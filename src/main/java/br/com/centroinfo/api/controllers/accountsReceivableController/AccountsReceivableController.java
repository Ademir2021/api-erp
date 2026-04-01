package br.com.centroinfo.api.controllers.accountsReceivableController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.centroinfo.api.dtos.accountsReceivableDTO.AccountsReceivableResponseDTO;

import br.com.centroinfo.api.services.accountsReceivable.AccountsReceivableServices;

@RestController
@RequestMapping("")
public class AccountsReceivableController {

    @Autowired
    AccountsReceivableServices accountsReceivableServices;

    @GetMapping("/accounts_receivable")
    List<AccountsReceivableResponseDTO> list (){
        return accountsReceivableServices.list();
    }

}
