package br.com.centroinfo.api.dtos.accountsReceivableDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.centroinfo.api.entities.accountsReceivable.PaymentAccountsReceivable;
import br.com.centroinfo.api.entities.accountsReceivable.SituationAccountsReceivable;
import br.com.centroinfo.api.entities.branchs.Branch;
import br.com.centroinfo.api.entities.persons.Person;
import br.com.centroinfo.api.entities.sales.Sale;
import br.com.centroinfo.api.entities.users.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountsReceivableDTO {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Branch branch;
    private User user;
    private Person payer;
    private Sale sale;
    private BigDecimal value;
    private BigDecimal receivedValue;
    private BigDecimal balance;
    private LocalDateTime dueDate;
    private String description;
    private SituationAccountsReceivable situation;
    private String observations;
    private BigDecimal lateFee;
    private BigDecimal interest;
    private BigDecimal discount;
    private PaymentAccountsReceivable type;
    private Long idTypeOperation;
    private String descriptionTypeOperation;

}
