package br.com.centroinfo.api.controllers.zipcodecontroller;

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
import br.com.centroinfo.api.dtos.zipcodeDTO.ZipcodeDTO;
import br.com.centroinfo.api.entities.address.zipcode.ZipCode;
import br.com.centroinfo.api.services.zipcode.ZipcodeService;

@RestController
@RequestMapping("")
public class ZipcodeController {

    @Autowired
    private ZipcodeService zipcodeService;

    // public ZipcodeController(ZipcodeService zipcodeService){
    //     this.zipcodeService = zipcodeService;
    // }

  @PostMapping("/zipcode")
    public ResponseEntity<?> create(@RequestBody ZipcodeDTO zipcodeDTO) {
        try {
            ZipCode zipCode = zipcodeService.create(zipcodeDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "CEP Registrado com sucesso",
                    "name", zipCode.getCode()));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao Registrar CEP",
                    "details", e.getMessage()));
        }
    }

    @GetMapping("/zipcodes")
    public List<ZipCode> list() {
        return zipcodeService.list();
    }

     @PutMapping("/zipcode/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody ZipcodeDTO zipCodeDTO) {
        zipCodeDTO.setId(id);
        try {
            ZipCode brand = zipcodeService.update(zipCodeDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "CEP atualizado com sucesso",
                    "id", brand.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao atualizar CEP",
                    "details", e.getMessage()));
        }
    }


}
