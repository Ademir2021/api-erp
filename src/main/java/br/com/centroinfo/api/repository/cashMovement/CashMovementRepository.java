package br.com.centroinfo.api.repository.cashMovement;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.centroinfo.api.entities.cashMovement.CashMovement;
import br.com.centroinfo.api.entities.cashMovement.MovementType;

public interface CashMovementRepository extends JpaRepository<CashMovement, Long> {

    @Query("SELECT COALESCE(SUM(cm.amount), 0) FROM CashMovement cm WHERE cm.accountsReceivable.id = :id")
    BigDecimal sumByAccountsReceivableId(@Param("id") Long id);

    @Query("""
            SELECT COALESCE(
                        SUM(
                            CASE
                                WHEN cm. movementType = :type
                                THEN cm.amount
                                ELSE -cm.amount
                            END
                        ), 0)
                    FROM CashMovement cm
                """)
    BigDecimal getCurrentBalance(@Param("type") MovementType type);
}
