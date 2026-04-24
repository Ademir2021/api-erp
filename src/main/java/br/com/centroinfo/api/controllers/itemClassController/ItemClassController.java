package br.com.centroinfo.api.controllers.itemClassController;

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
import br.com.centroinfo.api.dtos.itemClassDTO.ItemClassDTO;
import br.com.centroinfo.api.entities.items.itemClass.ItemClass;
import br.com.centroinfo.api.services.itemClass.ItemClassService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("")
public class ItemClassController {

    @Autowired
    ItemClassService itemClassService;

    @PostMapping("itemsclasse")
    public ResponseEntity<?> create(@RequestBody ItemClassDTO ItemClassDTO) {
        try {
            ItemClass itemClass = itemClassService.create(ItemClassDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Classe Registrada com sucesso",
                    "name", itemClass.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao Registrar Classe",
                    "details", e.getMessage()));
        }
    }

    @GetMapping("/itemsclasses")
    List<ItemClass> list() {
        return itemClassService.list();
    }

    @PutMapping("/itemsclasse/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody ItemClassDTO itemClassDTO) {
        itemClassDTO.setId(id);
        try {
            ItemClass itemClass = itemClassService.update(itemClassDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Classe atualizada com sucesso",
                    "id", itemClass.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao atualizar Classe",
                    "details", e.getMessage()));
        }
    }

}
