package br.com.centroinfo.api.controllers.itemcontroller;

import br.com.centroinfo.api.dtos.itemDTO.ItemDTO;
import br.com.centroinfo.api.entities.items.item.Item;
import br.com.centroinfo.api.services.item.ItemService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping("/item")
    public ResponseEntity<?> create(@RequestBody ItemDTO itemDTO) {
        try {
           Item item = itemService.create(itemDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Item Registrado com sucesso",
                    "name", item.getName()));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao Registrar Item",
                    "details", e.getMessage()));
        }
    }

    @GetMapping("/items")
    public List<Item> list() {
        return itemService.list();
    }

    // Endpoint para buscar itens por nome, barCode ou todos
    @GetMapping("/search_item")
    public List<Item> searchItems(@RequestParam(required = false) String name) {
        return itemService.searchItems(name);
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody ItemDTO itemDTO) {
        itemDTO.setId(id);
        try {
            Item item = itemService.update(itemDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Item atualizado com sucesso",
                    "id", item.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao atualizar Item",
                    "details", e.getMessage()));
        }
    }

    @DeleteMapping("/item/{id}")
    public List<Item> delete(@PathVariable("id") Long id) {
        return itemService.delete(id);
    }
}
