package br.com.centroinfo.api.services.cashMovement;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centroinfo.api.dtos.cashMovementDTO.CashMovementDTO;
// import br.com.centroinfo.api.entities.accountsReceivable.AccountsReceivable;
// import br.com.centroinfo.api.entities.accountsReceivable.SituationAccountsReceivable;
import br.com.centroinfo.api.entities.cashMovement.CashMovement;
import br.com.centroinfo.api.entities.cashMovement.MovementType;
import br.com.centroinfo.api.repository.accountsReceivable.AccountsReceivableRepository;
import br.com.centroinfo.api.repository.cashMovement.CashMovementRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CashMovementService {

    @Autowired
    private CashMovementRepository cashRepository;

    @Autowired
    private AccountsReceivableRepository accountsRepository;

    public BigDecimal getSaldoCaixa() {
        return cashRepository.getCurrentBalance(MovementType.CREDIT);
    }

    public CashMovement create(CashMovementDTO dto) {

        BigDecimal lastBalance = getSaldoCaixa();
        BigDecimal newBalance;

        if (dto.getMovementType() == MovementType.CREDIT) {
            newBalance = lastBalance.add(dto.getAmount());
        } else {
            newBalance = lastBalance.subtract(dto.getAmount());
        }

        // Criar movimento de caixa
        CashMovement movement = new CashMovement();
        movement.setAmount(dto.getAmount());
        movement.setMovementType(dto.getMovementType());
        movement.setDescription(dto.getDescription());
        movement.setAccountsReceivable(dto.getAccountsReceivableId() != null
                ? accountsRepository.findById(dto.getAccountsReceivableId())
                        .orElseThrow(() -> new RuntimeException("Conta não encontrada"))
                : null);
        movement.setBalanceAfter(newBalance);

        return cashRepository.save(movement);
    }

  public List<CashMovement> list() {
        return cashRepository.findAll();
    }
}
