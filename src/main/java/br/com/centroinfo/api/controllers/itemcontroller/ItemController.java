package br.com.centroinfo.api.controllers.itemcontroller;

import br.com.centroinfo.api.dtos.itemDTO.ItemDTO;
import br.com.centroinfo.api.entities.items.item.Item;
import br.com.centroinfo.api.services.item.ItemService;

import java.util.List;
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
            return (ResponseEntity<?>) itemService.create(itemDTO);

        } catch (Exception e) {
            // throw new ResourceNotFoundException("Item não gravado");
            return ResponseEntity.ok().body("Error: " + e);
        }
    }

    @GetMapping("/items")
    public List<Item> list() {
        return itemService.list();
    }

     // Endpoint para buscar itens por nome
    @GetMapping("/search_item")
    public List<Item> searchItemsByName(@RequestParam(required = false) String name) {
        return itemService.searchItemsByName(name);
    }


    @PutMapping("/item")
    public List<Item> update(@RequestBody ItemDTO itemDTO) {
        return itemService.update(itemDTO);
    }

    @DeleteMapping("/item/{id}")
    public List<Item> delete(@PathVariable("id") Long id) {
        return itemService.delete(id);
    }

}
