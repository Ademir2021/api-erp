package br.com.centroinfo.api.controllers.salecontroller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.centroinfo.api.dtos.saleDTO.SaleDTO;
import br.com.centroinfo.api.dtos.saleDTO.SaleResponseDTO;
import br.com.centroinfo.api.entities.sales.Sale;
import br.com.centroinfo.api.services.sale.SaleService;

@RestController
@RequestMapping("")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping("/sale")
    public ResponseEntity<?> create(@RequestBody SaleDTO saleDTO) {
        try {
            Sale sale = saleService.createSale(saleDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Venda Registrada com sucesso",
                    "id", sale.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao Registrar Venda",
                    "details", e.getMessage()));
        }
    }

    @PutMapping("sale/{id}/cancel")
    public ResponseEntity<?> cancelSale(
            @PathVariable Long id) {
        try {
            SaleDTO saleDto = new SaleDTO();
            saleDto.setId(id);
            Sale sale = saleService.cancelSale(saleDto);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Venda Cancelada com sucesso",
                    "id", sale.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao Cancelar Venda",
                    "details", e.getMessage()));
        }
    }

    // Ex:http://localhost:8080/sales?userId=1 ou http://localhost:8080
    @GetMapping("/sales")
    public List<SaleResponseDTO> listAllSalesByUsers(@RequestParam(required = false) Long userId) {
        return saleService.listAllSalesByUsers(userId);
    }

}
