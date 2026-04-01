package br.com.centroinfo.api.entities.accountsReceivable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.centroinfo.api.entities.branchs.Branch;
import br.com.centroinfo.api.entities.persons.Person;
import br.com.centroinfo.api.entities.sales.Sale;
import br.com.centroinfo.api.entities.users.User;

@Entity
@Table(name = "accounts_receivable")
@Getter
@Setter
public class AccountsReceivable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Relacionamentos (equivalente ao Pick<T, 'id'>)
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private Person payer;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = true)
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

    private Long idTypeOperation;
    private String descriptionTypeOperation;
}
