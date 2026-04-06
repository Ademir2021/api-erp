package br.com.centroinfo.api.dtos.cashMovementDTO;

import java.math.BigDecimal;
import br.com.centroinfo.api.entities.cashMovement.MovementType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CashMovementDTO {

    private BigDecimal amount;
    private MovementType movementType;
    private String description;
    private Long accountsReceivableId;

}
