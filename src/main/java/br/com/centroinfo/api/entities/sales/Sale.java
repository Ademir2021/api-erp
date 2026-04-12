package br.com.centroinfo.api.entities.sales;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import br.com.centroinfo.api.entities.accountsReceivable.AccountsReceivable;
import br.com.centroinfo.api.entities.branchs.Branch;
import br.com.centroinfo.api.entities.persons.Person;
import br.com.centroinfo.api.entities.users.User;
import lombok.Getter;
import lombok.Setter;

@Table(name = "sales")
@Entity
@Getter
@Setter
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "issue_date")
    private LocalDateTime issueDate;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @ManyToOne
    @JoinColumn(name = "operation_sale_id")
    private OperationSale operationSale;
    @Column(name = "total_sale")
    private BigDecimal totalSale;
    @Column(name = "discount")
    private BigDecimal discount;
    @Column(name = "total_note")
    private BigDecimal totalNote;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<ItemSale> itemsSale;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<AccountsReceivable> accountsReceivable;
}
