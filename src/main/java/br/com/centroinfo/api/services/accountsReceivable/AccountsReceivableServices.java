package br.com.centroinfo.api.services.accountsReceivable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.centroinfo.api.dtos.accountsReceivableDTO.AccountsReceivableDTO;
import br.com.centroinfo.api.dtos.accountsReceivableDTO.AccountsReceivableResponseDTO;
import br.com.centroinfo.api.entities.accountsReceivable.AccountsReceivable;
import br.com.centroinfo.api.entities.accountsReceivable.SituationAccountsReceivable;
import br.com.centroinfo.api.entities.cashMovement.CashMovement;
import br.com.centroinfo.api.entities.cashMovement.MovementType;
import br.com.centroinfo.api.repository.accountsReceivable.AccountsReceivableRepository;
import br.com.centroinfo.api.repository.cashMovement.CashMovementRepository;
import br.com.centroinfo.api.services.cashMovement.CashMovementService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccountsReceivableServices {

    @Autowired
    AccountsReceivableRepository accountsReceivableRepository;

    @Autowired
    private CashMovementRepository cashRepository;

    @Autowired
    private CashMovementService cashMovementService;

    private void mapFieldsAccountsReceivable(AccountsReceivable ar, AccountsReceivableDTO dto) {
        ar.setValue(dto.getValue());
        ar.setDueDate(dto.getDueDate());
        ar.setDescription(dto.getDescription());
        ar.setSituation(dto.getSituation());
        ar.setObservations(dto.getObservations());
        ar.setLateFee(dto.getLateFee());
        ar.setInterest(dto.getInterest());
        ar.setDiscount(dto.getDiscount());
        ar.setType(dto.getType());
        ar.setIdTypeOperation(dto.getIdTypeOperation());
        ar.setDescriptionTypeOperation(dto.getDescriptionTypeOperation());

        // Relacionamentos (se vierem preenchidos)
        ar.setBranch(dto.getBranch());
        ar.setUser(dto.getUser());
        ar.setPayer(dto.getPayer());
        ar.setSale(dto.getSale());
    }

    public AccountsReceivable update(Long id, AccountsReceivableDTO dto) {

        Optional<AccountsReceivable> account = accountsReceivableRepository.findById(id);
        if (!account.isPresent()) {
            throw new RuntimeException("Conta a receber não encontrada!");
        }
        AccountsReceivable ar = account.get();
        ar.setUpdatedAt(LocalDateTime.now());
        mapFieldsAccountsReceivable(ar, dto);

        // Soma direto no banco (melhor performace)
        BigDecimal totalReceived = cashRepository.sumByAccountsReceivableId(ar.getId());
        BigDecimal newtotalReceived = totalReceived
                .add(dto.getReceivedValue() != null ? dto.getReceivedValue() : BigDecimal.ZERO);

        // Atualiza saldo da conta
        BigDecimal interest = ar.getInterest() != null ? ar.getInterest() : BigDecimal.ZERO;
        BigDecimal lateFee = ar.getLateFee() != null ? ar.getLateFee() : BigDecimal.ZERO;
        BigDecimal discount = ar.getDiscount() != null ? ar.getDiscount() : BigDecimal.ZERO;
        BigDecimal total = ar.getValue()
                .add(interest)
                .add(lateFee)
                .subtract(discount);
        BigDecimal balanceRemaining = total.subtract(newtotalReceived);

        if (balanceRemaining.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Pagamento maior que o valor da conta!");
        }

        ar.setReceivedValue(newtotalReceived);
        ar.setBalance(balanceRemaining);

        // Atualiza status
        if (balanceRemaining.compareTo(BigDecimal.ZERO) == 0) {
            ar.setSituation(SituationAccountsReceivable.PAID);
        } else {
            ar.setSituation(SituationAccountsReceivable.PENDING);
        }

        // Criar movimento de caixa
        CashMovement movement = new CashMovement();
        movement.setAmount(dto.getReceivedValue());
        movement.setMovementType(MovementType.CREDIT);
        movement.setDescription(dto.getIdTypeOperation() + " - " + dto.getDescriptionTypeOperation());
        movement.setAccountsReceivable(ar);
        movement.setBalanceAfter(cashMovementService.getSaldoCaixa().add(dto.getReceivedValue()));
        cashRepository.save(movement);

        return accountsReceivableRepository.save(ar);
    }

    public List<AccountsReceivableResponseDTO> findAccountsReceivableAll() {
        return accountsReceivableRepository.findAccountsReceivableAll();
    }

    public List<AccountsReceivableResponseDTO> findAccountsReceivableById(Long arId) {
        return accountsReceivableRepository.findAccountsReceivableById(arId);
    }

    public List<AccountsReceivableResponseDTO> findAllAccountsReceivableByUser(Long userId) {
        return accountsReceivableRepository.findAllAccountsReceivableByUser(userId);
    }

}
