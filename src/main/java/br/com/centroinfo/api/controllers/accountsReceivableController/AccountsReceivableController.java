package br.com.centroinfo.api.controllers.accountsReceivableController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.centroinfo.api.dtos.accountsReceivableDTO.AccountsReceivableDTO;
import br.com.centroinfo.api.dtos.accountsReceivableDTO.AccountsReceivableResponseDTO;
import br.com.centroinfo.api.entities.accountsReceivable.AccountsReceivable;
import br.com.centroinfo.api.services.accountsReceivable.AccountsReceivableServices;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("")
public class AccountsReceivableController {

    @Autowired
    AccountsReceivableServices accountsReceivableServices;

    @PutMapping("/account_receivable/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody AccountsReceivableDTO dto) {
        try {
            AccountsReceivable ar = accountsReceivableServices.update(id, dto);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Titulo atualizado com sucesso",
                    "id", ar.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao atualizar Titulo",
                    "details", e.getMessage()));
        }
    }

    @GetMapping("/accounts_receivable")
    List<AccountsReceivableResponseDTO> list() {
        return accountsReceivableServices.list();
    }
}
