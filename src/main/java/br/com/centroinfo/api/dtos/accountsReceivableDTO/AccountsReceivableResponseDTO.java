package br.com.centroinfo.api.dtos.accountsReceivableDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.centroinfo.api.entities.accountsReceivable.PaymentAccountsReceivable;
import br.com.centroinfo.api.entities.accountsReceivable.SituationAccountsReceivable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountsReceivableResponseDTO {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BranchResponseDTO branch;
    private UserResponseDTO user;
    private PersonResponseDTO payer; // Pagador do Titulo
    private SaleResponseDTO sale;
    private BigDecimal value;
    private BigDecimal receivedValue;
    private BigDecimal balance;
    private LocalDateTime dueDate;
    private Boolean cancel;
    private String description;
    private SituationAccountsReceivable situation;
    private String observations;
    private BigDecimal lateFee;
    private BigDecimal interest;
    private BigDecimal discount;
    private PaymentAccountsReceivable type;
    private String idTypeOperation;
    private String descriptionTypeOperation;

    public AccountsReceivableResponseDTO(
            Long id, LocalDateTime createdAt, LocalDateTime updatedAt,
            Long iB, String nB, Long iU, String nU, Long iP, String nP,
            Long iS, BigDecimal value, BigDecimal receivedValue, BigDecimal balance,
            LocalDateTime dueDate, Boolean cancel, String description, SituationAccountsReceivable situation,
            String observations, BigDecimal lateFee, BigDecimal interest, BigDecimal discount,
            PaymentAccountsReceivable type, String idTypeOperation, String descriptionTypeOperation) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.branch = new BranchResponseDTO(iB, nB);
        this.user = new UserResponseDTO(iU, nU);
        this.payer = new PersonResponseDTO(iP, nP);
        this.sale = new SaleResponseDTO(iS);
        this.value = value;
        this.receivedValue = receivedValue;
        this.balance = balance;
        this.dueDate = dueDate;
        this.cancel = cancel;
        this.description = description;
        this.situation = situation;
        this.observations = observations;
        this.lateFee = lateFee;
        this.interest = interest;
        this.discount = discount;
        this.type = type;
        this.idTypeOperation = idTypeOperation;
        this.descriptionTypeOperation = descriptionTypeOperation;

    };

    private final record BranchResponseDTO(Long id, String name) {
    }

    private final record UserResponseDTO(Long id, String login) {
    }

    private final record PersonResponseDTO(Long id, String name) {
    }

    private final record SaleResponseDTO(Long id) {
    }

}
