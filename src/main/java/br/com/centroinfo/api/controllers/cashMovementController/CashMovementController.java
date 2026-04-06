package br.com.centroinfo.api.controllers.cashMovementController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.centroinfo.api.dtos.cashMovementDTO.CashMovementDTO;
import br.com.centroinfo.api.entities.cashMovement.CashMovement;
import br.com.centroinfo.api.services.cashMovement.CashMovementService;

@RestController
@RequestMapping("/cash")
public class CashMovementController {

    @Autowired
    private CashMovementService service;

    @PostMapping
    public ResponseEntity<CashMovement> create(@RequestBody CashMovementDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("list")
    public List<CashMovement> list() {
        return service.list();
    }

}
