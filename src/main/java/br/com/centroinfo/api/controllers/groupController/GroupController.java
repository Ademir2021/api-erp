package br.com.centroinfo.api.controllers.groupController;

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
import br.com.centroinfo.api.dtos.groupDTO.GroupDTO;
import br.com.centroinfo.api.entities.items.group.Group;
import br.com.centroinfo.api.services.group.GroupItemService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("")
public class GroupController {

    @Autowired
    GroupItemService groupItemService;

    @PostMapping("/group")
    public ResponseEntity<?> create(@RequestBody GroupDTO itemDTO) {
        try {
            Group group = groupItemService.create(itemDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Grupo Registrado com sucesso",
                    "name", group.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao Registrar Grupo",
                    "details", e.getMessage()));
        }
    }

    @GetMapping("/groups")
    public List<Group> list() {
        return groupItemService.list();
    }

    @PutMapping("/group/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody GroupDTO groupDTO) {
        groupDTO.setId(id);
        try {
            Group group = groupItemService.update(groupDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Grupo atualizado com sucesso",
                    "id", group.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao atualizar Grupo",
                    "details", e.getMessage()));
        }
    }
}
