package br.com.centroinfo.api.controllers.brandcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Brand> create(@RequestBody BrandDTO brandDTO) {
        // Brand brand = brandService.create(brandDTO);
            brandService.create(brandDTO);
        return brandService.list();
    }

    @GetMapping("/brands")
    List<Brand> list() {
        return brandService.list();
    }

    @PutMapping("/brand")
    public List<Brand> update(@RequestBody BrandDTO brandDTO) {
        // Brand brand = brandService.update(brandDTO);
        brandService.update(brandDTO);
        return brandService.list();
    }

    @DeleteMapping("/brand/{id}")
     public List<Brand> delete(@PathVariable("id") Long id){
        brandService.delete(id);
        return brandService.list();
    }

}
