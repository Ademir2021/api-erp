package br.com.centroinfo.api.entities.accountsReceivable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.centroinfo.api.entities.branchs.Branch;
import br.com.centroinfo.api.entities.persons.Person;
import br.com.centroinfo.api.entities.sales.Sale;
import br.com.centroinfo.api.entities.users.User;

@Table(name = "accounts_receivable")
@Entity
@Getter
@Setter
public class AccountsReceivable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    @JsonIgnore
    private Branch branch;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @ManyToOne
    @JoinColumn(name = "payer_id")
    @JsonIgnore
    private Person payer;
    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = true)
    @JsonIgnore
    private Sale sale;
    private BigDecimal value;
    private BigDecimal receivedValue;
    private BigDecimal balance;
    private LocalDateTime dueDate;
    private String description;
    @Enumerated(EnumType.STRING)
    private SituationAccountsReceivable situation;
    private String observations;
    private BigDecimal lateFee;
    private BigDecimal interest;
    private BigDecimal discount;
    @Enumerated(EnumType.STRING)
    private PaymentAccountsReceivable type;
    private String idTypeOperation;
    private String descriptionTypeOperation;
}
