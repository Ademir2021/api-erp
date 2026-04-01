package br.com.centroinfo.api.services.sale;

import br.com.centroinfo.api.dtos.saleDTO.ItemSaleDTO;
import br.com.centroinfo.api.dtos.saleDTO.SaleDTO;
import br.com.centroinfo.api.entities.accountsReceivable.AccountsReceivable;
import br.com.centroinfo.api.entities.sales.ItemSale;
import br.com.centroinfo.api.entities.sales.Sale;
import br.com.centroinfo.api.repository.sale.SaleRepository;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    private Double totalSale = 0.0;

    public Sale createSale(SaleDTO saleDTO) {
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
            item.setTotalItem(itemDTO.getPrice() * itemDTO.getAmount());
            totalSale += item.getTotalItem();
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
                    accountsReceivable.setIdTypeOperation(accountsReceivableDTO.getIdTypeOperation());
                    accountsReceivable.setDescriptionTypeOperation(accountsReceivableDTO.getDescriptionTypeOperation());
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
        sale.setTotalNote(totalSale - sale.getDiscount());
        return saleRepository.save(sale);
    }

    public List<Sale> listSale() {
        return saleRepository.findAll();
    }

}
