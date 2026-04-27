package br.com.centroinfo.api.controllers.stateController;

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

import br.com.centroinfo.api.dtos.stateDTO.StateDTO;
import br.com.centroinfo.api.entities.address.state.State;
import br.com.centroinfo.api.services.state.StateServices;

@RestController
@RequestMapping("")
public class StateController {

    @Autowired
    StateServices stateServices;

    @PostMapping("/state")
    public ResponseEntity<?> create(@RequestBody StateDTO itemDTO) {
        try {
            State brand = stateServices.create(itemDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Estado Registrado com sucesso",
                    "name", brand.getName()));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao Registrar Estado",
                    "details", e.getMessage()));
        }
    }

    @GetMapping("/states")
   public List<State> list() {
        return stateServices.list();
    }

    @PutMapping("/state/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody StateDTO stateDTO) {
        stateDTO.setId(id);
        try {
            State brand = stateServices.update(stateDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Estado atualizado com sucesso",
                    "id", brand.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao atualizar Estado",
                    "details", e.getMessage()));
        }
    }

}
