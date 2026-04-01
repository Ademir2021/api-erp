package br.com.centroinfo.api.services.accountsReceivable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centroinfo.api.dtos.accountsReceivableDTO.AccountsReceivableResponseDTO;
import br.com.centroinfo.api.repository.accountsReceivable.AccountsReceivableRepository;

@Service
public class AccountsReceivableServices {

    @Autowired
    AccountsReceivableRepository accountsReceivableRepository;

   public List<AccountsReceivableResponseDTO> list() {
        return accountsReceivableRepository.findAccountsReceivable();
    }

}
