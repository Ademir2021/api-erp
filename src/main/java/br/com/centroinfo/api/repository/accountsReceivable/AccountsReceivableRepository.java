package br.com.centroinfo.api.repository.accountsReceivable;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.centroinfo.api.dtos.accountsReceivableDTO.AccountsReceivableResponseDTO;
import br.com.centroinfo.api.entities.accountsReceivable.AccountsReceivable;

public interface AccountsReceivableRepository extends JpaRepository<AccountsReceivable, Long> {

    String ACCOUNTS_RECEIVALBE_RESPONSE =("""
    SELECT new br.com.centroinfo.api.dtos.accountsReceivableDTO.AccountsReceivableResponseDTO(
        ar.id,
        ar.createdAt,
        ar.updatedAt,
        b.id,
        b.name,
        u.id,
        u.login,
        p.id,
        p.name,
        s.id,
        ar.value,
        ar.receivedValue,
        ar.balance,
        ar.dueDate,
        ar.description,
        ar.situation,
        ar.observations,
        ar.lateFee,
        ar.interest,
        ar.discount,
        ar.type,
        ar.idTypeOperation,
        ar.descriptionTypeOperation
    )
    FROM AccountsReceivable ar
    LEFT JOIN ar.branch b
    LEFT JOIN ar.user u
    LEFT JOIN ar.payer p
    LEFT JOIN ar.sale s
""");
  

    @Query(ACCOUNTS_RECEIVALBE_RESPONSE)
    List<AccountsReceivableResponseDTO> findAccountsReceivable();

}
