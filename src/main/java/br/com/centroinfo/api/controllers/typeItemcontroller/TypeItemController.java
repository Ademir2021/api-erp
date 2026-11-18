package br.com.centroinfo.api.controllers.typeItemcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.centroinfo.api.dtos.typeItemDTO.TypeItemDTO;
import br.com.centroinfo.api.entities.items.typeItem.TypeItem;
import br.com.centroinfo.api.services.typeItem.TypeItemServices;

@RestController
@RequestMapping("")
public class TypeItemController {

    @Autowired
    private TypeItemServices typeItemServices;

    @PostMapping("/typeitem")
    public ResponseEntity<?> create(@RequestBody TypeItemDTO typeItemDTO) {
        try {
            TypeItem typeItem = typeItemServices.create(typeItemDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Tipo de Item Registrado com sucesso",
                    "name", typeItem.getName()));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao Registrar Tipo de ITem",
                    "details", e.getMessage()));
        }
    }

    @GetMapping("/typeitems")
    public List<TypeItem> list() {
        return typeItemServices.list();
    }

    @PutMapping("/typeitem/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody TypeItemDTO typeItemDTO) {
        typeItemDTO.setId(id);
        try {
            TypeItem typeItem = typeItemServices.update(typeItemDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Tipo de Item atualizado com sucesso",
                    "id", typeItem.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao atualizar Tipo de Item",
                    "details", e.getMessage()));
        }
    }

}
