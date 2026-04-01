package br.com.centroinfo.api.dtos.saleDTO;

import java.util.List;

import br.com.centroinfo.api.dtos.accountsReceivableDTO.AccountsReceivableDTO;
import br.com.centroinfo.api.entities.branchs.Branch;
import br.com.centroinfo.api.entities.persons.Person;
import br.com.centroinfo.api.entities.sales.OperationSale;
import br.com.centroinfo.api.entities.users.User;
import lombok.Getter;

@Getter
public class SaleDTO {
    private Branch branch;
    private User user;
    private Person person;
    private OperationSale operationSale;
    private Double discount;
    private List<ItemSaleDTO> itemsSale;
    private List<AccountsReceivableDTO> accountsReceivable;
}