package br.com.centroinfo.api.controllers.brandcontroller;

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

import br.com.centroinfo.api.dtos.brandDTO.BrandDTO;

import br.com.centroinfo.api.entities.items.brand.Brand;
import br.com.centroinfo.api.services.brand.BrandService;

@RestController
@RequestMapping("")
public class BrandController {

    @Autowired
    BrandService brandService;

    @PostMapping("/brand")
    public ResponseEntity<?> create(@RequestBody BrandDTO itemDTO) {
        try {
            Brand brand = brandService.create(itemDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Marca Registrado com sucesso",
                    "name", brand.getName()));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao Registrar Marca",
                    "details", e.getMessage()));
        }
    }

    @GetMapping("/brands")
    List<Brand> list() {
        return brandService.list();
    }

    @PutMapping("/brand/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody BrandDTO brandDTO) {
        brandDTO.setId(id);
        try {
            Brand brand = brandService.update(brandDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Marca atualizado com sucesso",
                    "id", brand.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao atualizar Marca",
                    "details", e.getMessage()));
        }
    }

    @DeleteMapping("/brand/{id}")
    public List<Brand> delete(@PathVariable("id") Long id) {
        brandService.delete(id);
        return brandService.list();
    }

}
