package br.com.centroinfo.api.services.sale;

import br.com.centroinfo.api.dtos.saleDTO.ItemSaleDTO;
import br.com.centroinfo.api.dtos.saleDTO.SaleDTO;
import br.com.centroinfo.api.entities.accountsReceivable.AccountsReceivable;
import br.com.centroinfo.api.entities.cashMovement.CashMovement;
import br.com.centroinfo.api.entities.cashMovement.MovementType;
import br.com.centroinfo.api.entities.sales.ItemSale;
import br.com.centroinfo.api.entities.sales.Sale;
import br.com.centroinfo.api.repository.cashMovement.CashMovementRepository;
import br.com.centroinfo.api.repository.sale.SaleRepository;
import br.com.centroinfo.api.services.cashMovement.CashMovementService;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SaleService {

        @Autowired
        private SaleRepository saleRepository;

        @Autowired
        private CashMovementRepository cashRepository;

        @Autowired
        private CashMovementService cashMovementService;

        public Sale createSale(SaleDTO saleDTO) {

                BigDecimal totalSale = BigDecimal.ZERO;
                BigDecimal totalNote = BigDecimal.ZERO;
                long nextNumber = 0;

                Sale sale = new Sale();
                sale.setIssueDate(LocalDateTime.now());
                sale.setPerson(saleDTO.getPerson());
                sale.setOperationSale(saleDTO.getOperationSale());
                sale.setDiscount(saleDTO.getDiscount());
                sale.setBranch(saleDTO.getBranch());
                sale.setUser(saleDTO.getUser());

                List<ItemSale> itemList = new ArrayList<>();
                for (ItemSaleDTO itemDTO : saleDTO.getItemsSale()) {
                        ItemSale item = new ItemSale();
                        item.setItem(itemDTO.getItem());
                        item.setAmount(itemDTO.getAmount());
                        item.setPrice(itemDTO.getPrice());
                        item.setTotalItem(itemDTO.getPrice().multiply(itemDTO.getAmount()));
                        totalSale = totalSale.add(item.getTotalItem());
                        item.setSale(sale);
                        itemList.add(item);
                }

                List<AccountsReceivable> accountsReceivableList = new ArrayList<>();
                Optional.ofNullable(saleDTO.getAccountsReceivable())
                                .orElse(Collections.emptyList())
                                .forEach(accountsReceivableDTO -> {
                                        AccountsReceivable accountsReceivable = new AccountsReceivable();
                                        accountsReceivable.setDueDate(accountsReceivableDTO.getDueDate());
                                        accountsReceivable.setSale(sale);
                                        accountsReceivable.setPayer(accountsReceivableDTO.getPayer());
                                        accountsReceivable.setValue(accountsReceivableDTO.getValue());
                                        accountsReceivable.setDescription(accountsReceivableDTO.getDescription());
                                        accountsReceivable.setSituation(accountsReceivableDTO.getSituation());
                                        accountsReceivable.setType(accountsReceivableDTO.getType());
                                        accountsReceivable
                                                        .setIdTypeOperation(accountsReceivableDTO.getIdTypeOperation());
                                        accountsReceivable.setDescriptionTypeOperation(
                                                        accountsReceivableDTO.getDescriptionTypeOperation());
                                        accountsReceivable.setBranch(accountsReceivableDTO.getBranch());
                                        accountsReceivable.setUser(accountsReceivableDTO.getUser());
                                        accountsReceivable.setObservations(accountsReceivableDTO.getObservations());
                                        accountsReceivable.setLateFee(accountsReceivableDTO.getLateFee());
                                        accountsReceivable.setInterest(accountsReceivableDTO.getInterest());
                                        accountsReceivable.setDiscount(accountsReceivableDTO.getDiscount());
                                        accountsReceivable.setBalance(accountsReceivableDTO.getValue());
                                        accountsReceivable.setReceivedValue(accountsReceivableDTO.getReceivedValue());
                                        accountsReceivable.setCreatedAt(LocalDateTime.now());
                                        accountsReceivableList.add(accountsReceivable);
                                });

                sale.setAccountsReceivable(accountsReceivableList);
                sale.setItemsSale(itemList);
                sale.setTotalSale(totalSale);
                sale.setTotalNote(totalSale.subtract(sale.getDiscount()));

                BigDecimal totalAccountsReceivable = accountsReceivableList.stream()
                                .map(ar -> ar.getValue() != null
                                                ? ar.getValue().setScale(2, RoundingMode.HALF_UP)
                                                : BigDecimal.ZERO)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                                .setScale(2, RoundingMode.HALF_UP);

                totalNote = sale.getTotalNote() != null
                                ? sale.getTotalNote().setScale(2, RoundingMode.HALF_UP)
                                : BigDecimal.ZERO;

                if (totalAccountsReceivable.compareTo(totalNote) < 0) {
                        BigDecimal lastBalance = totalNote
                                        .subtract(totalAccountsReceivable)
                                        .setScale(2, RoundingMode.HALF_UP);
                        CashMovement movement = new CashMovement();
                        movement.setAmount(lastBalance);
                        movement.setMovementType(MovementType.CREDIT);
                        nextNumber = saleRepository.count();
                        movement.setDescription("Venda - Nº:" + (nextNumber != 0 ? nextNumber + 1 : 1));
                        movement.setAccountsReceivable(null);
                        movement.setBalanceAfter(
                                        cashMovementService.getSaldoCaixa().add(lastBalance));
                        cashRepository.save(movement);
                }
                return saleRepository.save(sale);
        }

        public List<Sale> listSale() {
                return saleRepository.findAll();
        }

}
