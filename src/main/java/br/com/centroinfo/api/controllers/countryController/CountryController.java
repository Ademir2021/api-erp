package br.com.centroinfo.api.controllers.countryController;

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

import br.com.centroinfo.api.dtos.countryDTO.CountryDTO;
import br.com.centroinfo.api.entities.address.country.Country;
import br.com.centroinfo.api.services.country.CountryService;

@RestController
@RequestMapping("")
public class CountryController {

    @Autowired
    CountryService countryService;

    @PostMapping("/country")
    public ResponseEntity<?> create(@RequestBody CountryDTO countryDTODTO) {
        try {
            Country country = countryService.create(countryDTODTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "País Registrado com sucesso",
                    "name", country.getName()));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao Registrar País",
                    "details", e.getMessage()));
        }
    }

    @GetMapping("/countrys")
    public List<Country> list() {
        return countryService.list();
    }

    @PutMapping("/country/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody CountryDTO countryDTO) {
        countryDTO.setId(id);
        try {
            Country country = countryService.update(countryDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "País atualizado com sucesso",
                    "id", country.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao atualizar País",
                    "details", e.getMessage()));
        }
    }
}
