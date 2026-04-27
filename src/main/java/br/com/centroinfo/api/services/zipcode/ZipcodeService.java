package br.com.centroinfo.api.services.zipcode;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centroinfo.api.dtos.zipcodeDTO.ZipcodeDTO;
import br.com.centroinfo.api.entities.address.zipcode.ZipCode;
import br.com.centroinfo.api.repository.zipcode.ZipcodeRepository;

@Service
public class ZipcodeService {

    @Autowired
    ZipcodeRepository zipcodeRepository;

       public ZipCode create(ZipcodeDTO zipcodeDTO){
        ZipCode zipCode = new ZipCode();
        zipCode.setCode(zipcodeDTO.getCode());
        zipCode.setCity(zipcodeDTO.getCity());
        zipcodeRepository.save(zipCode);
        return zipCode;
    }

    public List<ZipCode> list() {
        return zipcodeRepository.findAll();
    }

     public ZipCode update(ZipcodeDTO zipcodeDTO){
        ZipCode zipCode = new ZipCode();
        zipCode.setId(zipcodeDTO.getId());
        zipCode.setCode(zipcodeDTO.getCode());
        zipCode.setCity(zipcodeDTO.getCity());
        zipcodeRepository.save(zipCode);
        return zipCode;
    }
}
