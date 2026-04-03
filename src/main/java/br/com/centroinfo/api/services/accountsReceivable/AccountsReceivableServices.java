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
import br.com.centroinfo.api.repository.accountsReceivable.AccountsReceivableRepository;

@Service
public class AccountsReceivableServices {

    @Autowired
    AccountsReceivableRepository accountsReceivableRepository;

    public AccountsReceivable update(Long id, AccountsReceivableDTO dto) {
        Optional<AccountsReceivable> optional = accountsReceivableRepository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Conta a receber não encontrada!");
        }
        AccountsReceivable entity = optional.get();

        entity.setUpdatedAt(LocalDateTime.now());

        // Atualizando campos
        entity.setValue(dto.getValue());
        entity.setReceivedValue(dto.getReceivedValue());
        entity.setDueDate(dto.getDueDate());
        entity.setDescription(dto.getDescription());
        entity.setSituation(dto.getSituation());
        entity.setObservations(dto.getObservations());
        entity.setLateFee(dto.getLateFee());
        entity.setInterest(dto.getInterest());
        entity.setDiscount(dto.getDiscount());
        entity.setType(dto.getType());
        entity.setIdTypeOperation(dto.getIdTypeOperation());
        entity.setDescriptionTypeOperation(dto.getDescriptionTypeOperation());

         // Calculo Automatico do saldo.
        BigDecimal value = entity.getValue() != null ? entity.getValue() : BigDecimal.ZERO;
        BigDecimal received = entity.getReceivedValue() != null ? entity.getReceivedValue() : BigDecimal.ZERO;
        entity.setBalance(value.subtract(received));

        // Relacionamentos (se vierem preenchidos)
        entity.setBranch(dto.getBranch());
        entity.setUser(dto.getUser());
        entity.setPayer(dto.getPayer());
        entity.setSale(dto.getSale());

        return accountsReceivableRepository.save(entity);
    }

    public List<AccountsReceivableResponseDTO> list() {
        return accountsReceivableRepository.findAccountsReceivable();
    }

}
