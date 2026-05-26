package br.com.centroinfo.api.services.sale;

import br.com.centroinfo.api.dtos.saleDTO.ItemSaleDTO;
import br.com.centroinfo.api.dtos.saleDTO.SaleDTO;
import br.com.centroinfo.api.dtos.saleDTO.SaleResponseDTO;
import br.com.centroinfo.api.entities.accountsReceivable.AccountsReceivable;
import br.com.centroinfo.api.entities.cashMovement.CashMovement;
import br.com.centroinfo.api.entities.cashMovement.MovementType;
import br.com.centroinfo.api.entities.sales.ItemSale;
import br.com.centroinfo.api.entities.sales.Sale;
import br.com.centroinfo.api.repository.accountsReceivable.AccountsReceivableRepository;
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
import java.util.Set;

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

        @Autowired
        private AccountsReceivableRepository accountsReceivableRepository;

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
                sale.setCancel(false);

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
                                        accountsReceivable.setCancel(false);
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

                final Long op = saleDTO.getOperationSale().getId();
                Set<Long> opsValidas = Set.of(1L, 2L, 3L);

                if (opsValidas.contains(op)) {
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
                }

                return saleRepository.save(sale);
        }

        @Transactional
        public Sale cancelSale(SaleDTO saleDTO) {
                Sale sale = saleRepository.findById(saleDTO.getId())
                                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
                // Evita cancelar duas vezes
                if (Boolean.TRUE.equals(sale.getCancel())) {
                        throw new RuntimeException("Venda já cancelada");
                }
                // Buscar contas da venda
                List<AccountsReceivable> accounts = accountsReceivableRepository.findBySaleId(sale.getId());
                // Somar contas a receber
                BigDecimal totalAccounts = accounts.stream()
                                .map(AccountsReceivable::getValue)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);
                // Cancelar contas
                accounts.forEach(account -> {
                        account.setCancel(true);
                });
                accountsReceivableRepository.saveAll(accounts);
                // Cancelar venda
                sale.setCancel(true);
                // Movimento de caixa
                BigDecimal totalMovement = sale.getTotalNote().subtract(totalAccounts);
                CashMovement movement = new CashMovement();
                movement.setAmount(totalMovement);
                movement.setMovementType(MovementType.DEBIT);
                movement.setDescription(
                                "Cancelamento da Venda: " + sale.getId());
                movement.setAccountsReceivable(null);
                // DEBIT diminui saldo
                movement.setBalanceAfter(
                                cashMovementService.getSaldoCaixa()
                                                .subtract(totalMovement));
                cashRepository.save(movement);
                return saleRepository.save(sale);
        }

        public List<SaleResponseDTO> listAllSalesByUsers(Long id) {
                return saleRepository.listAllSalesByUsers(id);
        }

}
