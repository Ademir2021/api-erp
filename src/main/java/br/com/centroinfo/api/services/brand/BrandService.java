package br.com.centroinfo.api.services.brand;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centroinfo.api.dtos.brandDTO.BrandDTO;
import br.com.centroinfo.api.entities.items.brand.Brand;
import br.com.centroinfo.api.repository.brand.BrandRepository;

@Service
public class BrandService {

    @Autowired
    BrandRepository brandRepository;

    public Brand create(BrandDTO brandDTO) {
        Brand brand = new Brand();
        brand.setName(brandDTO.name);
        brandRepository.save(brand);
        return brand;
    }

    public List<Brand> list() {
        return brandRepository.findAll();
    }

    public Brand update(BrandDTO brandDTO) {
        Brand brand = new Brand();
        brand.setId(brandDTO.id);
        brand.setName(brandDTO.name);
        brandRepository.save(brand);
        return brand;
    }

    public void delete(Long id) {
        brandRepository.deleteById(id);
    }

}
