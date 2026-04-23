package br.com.centroinfo.api.controllers.subGroupController;

import br.com.centroinfo.api.dtos.subGroupDTO.SubGroupDTO;
import br.com.centroinfo.api.entities.items.subGroup.SubGroup;
import br.com.centroinfo.api.services.subGroup.SubGroupService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class SubGroupController {

    @Autowired
    private SubGroupService subGroupService;

    @PostMapping("/subgroup")
    public ResponseEntity<?> create(@RequestBody SubGroupDTO subGroupDTO) {
        try {
            SubGroup subGroup = subGroupService.create(subGroupDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "SubGrupo Registrado com sucesso",
                    "name", subGroup.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao Registrar SubGrupo",
                    "details", e.getMessage()));
        }
    }

    @GetMapping("/sub_groups")
    List<SubGroup> list() {
        return subGroupService.list();
    }

    @PutMapping("/subgroup/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody SubGroupDTO subGroupDTO) {
        subGroupDTO.setId(id);
        try {
            SubGroup brand = subGroupService.update(subGroupDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "SubGrupo atualizado com sucesso",
                    "id", brand.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao atualizar SubGrupo",
                    "details", e.getMessage()));
        }
    }

    @DeleteMapping("/subgroups/{id}")
    List<SubGroup> delete(@PathVariable("id") Long id) {
        return subGroupService.delete(id);
    }
}
