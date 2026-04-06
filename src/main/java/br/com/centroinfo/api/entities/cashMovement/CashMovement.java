package br.com.centroinfo.api.entities.cashMovement;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.centroinfo.api.entities.accountsReceivable.AccountsReceivable;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cash_movement")
@Getter
@Setter
public class CashMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private MovementType movementType;
    private String description;
    private LocalDateTime movementDate;
    private BigDecimal balanceAfter;
    @ManyToOne(optional = true)
    @JoinColumn(name = "accounts_receivable_id", nullable = true)
    private AccountsReceivable accountsReceivable;

    @PrePersist
    public void prePersist() {
        this.movementDate = LocalDateTime.now();
    }
}
