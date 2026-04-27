package br.com.centroinfo.api.controllers.cityController;

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
import br.com.centroinfo.api.dtos.cityDTO.CityDTO;
import br.com.centroinfo.api.entities.address.city.City;
import br.com.centroinfo.api.services.city.CityServices;

@RestController
@RequestMapping("")
public class CityController {

    @Autowired
    CityServices cityServices;

    @PostMapping("/city")
    public ResponseEntity<?> create(@RequestBody CityDTO cityDTO) {
        try {
            City city = cityServices.create(cityDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Cidade Registrada com sucesso",
                    "name", city.getName()));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao Registrar Cidade",
                    "details", e.getMessage()));
        }
    }

    @GetMapping("/citys")
    List<City> list() {
        return cityServices.list();
    }

    @PutMapping("/city/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody CityDTO cityDTO) {
        cityDTO.setId(id);
        try {
            City city = cityServices.update(cityDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Cidade atualizada com sucesso",
                    "id", city.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao atualizar Cidade",
                    "details", e.getMessage()));
        }
    }
}
