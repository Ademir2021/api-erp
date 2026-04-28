package br.com.centroinfo.api.controllers.operationSaleController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.centroinfo.api.dtos.operationSaleDTO.OperationSaleDTO;
import br.com.centroinfo.api.entities.sales.OperationSale;
import br.com.centroinfo.api.services.operationSale.OperationSaleServices;

@RestController
@RequestMapping("")
public class OperationSaleController {

    @Autowired
    OperationSaleServices operationSaleServices;

    @PostMapping("/operationsale")
    public ResponseEntity<?> create(@RequestBody OperationSaleDTO operationSaleDTO) {
        try {
            OperationSale operationSale = operationSaleServices.create(operationSaleDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Operação Registrado com sucesso",
                    "name", operationSale.getDescription()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao Registrar Operação",
                    "details", e.getMessage()));
        }
    }

    @GetMapping("/operationsales")
    List<OperationSale> list() {
        return operationSaleServices.list();
    }

    @PutMapping("/operationsale/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody OperationSaleDTO operationSaleDTO) {
        operationSaleDTO.setId(id);
        try {
            OperationSale operationSale = operationSaleServices.update(operationSaleDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Operação atualizada com sucesso",
                    "id", operationSale.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao atualizar Operação",
                    "details", e.getMessage()));
        }
    }

}
