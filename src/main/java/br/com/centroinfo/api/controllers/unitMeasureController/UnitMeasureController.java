package br.com.centroinfo.api.controllers.unitMeasureController;

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

import br.com.centroinfo.api.dtos.unitMeasureDTO.UnitMeasureDTO;
import br.com.centroinfo.api.entities.items.unitMeasure.UnitMeasure;
import br.com.centroinfo.api.services.unitMeasure.UnitMeasureServices;

@RestController
@RequestMapping("")
public class UnitMeasureController {

    @Autowired
    UnitMeasureServices unitMeasureServices;

     @PostMapping("/unitmeasure")
    public ResponseEntity<?> create(@RequestBody UnitMeasureDTO unitMeasureDTO) {
        try {
            UnitMeasure unitMeasure = unitMeasureServices.create(unitMeasureDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Unidade de Medida Registrado com sucesso",
                    "name", unitMeasure.getName()));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao Registrar Unidade de Medida",
                    "details", e.getMessage()));
        }
    }

    @GetMapping("/unitmeasures")
    List<UnitMeasure> list() {
        return unitMeasureServices.list();
    }

     @PutMapping("/unitmeasure/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody UnitMeasureDTO unitMeasureDTO) {
        unitMeasureDTO.setId(id);
        try {
            UnitMeasure unitMeasure = unitMeasureServices.update(unitMeasureDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Unidade de Medida atualizado com sucesso",
                    "id", unitMeasure.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao atualizar Unidade",
                    "details", e.getMessage()));
        }
    }

}
