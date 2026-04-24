package br.com.centroinfo.api.services.country;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centroinfo.api.dtos.countryDTO.CountryDTO;
import br.com.centroinfo.api.entities.address.country.Country;
import br.com.centroinfo.api.repository.country.CountryRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public Country create(CountryDTO countryDTO){
        Country country = new Country();
        country.setName(countryDTO.getName());
        country.setAcronym(countryDTO.getAcronym());
        country.setDdi(countryDTO.getDdi());
        country.setCodeCountry(countryDTO.getCodeCountry());
        country.setCodeRevenue(countryDTO.getCodeRevenue());
        countryRepository.save(country);
        return country;
    }

    public List<Country> list(){
        return countryRepository.findAll();
    }

     public Country update(CountryDTO countryDTO){
        Country country = new Country();
        country.setId(countryDTO.getId());
        country.setName(countryDTO.getName());
        country.setAcronym(countryDTO.getAcronym());
        country.setDdi(countryDTO.getDdi());
        country.setCodeCountry(countryDTO.getCodeCountry());
        country.setCodeRevenue(countryDTO.getCodeRevenue());
        countryRepository.save(country);
        return country;
    }
}
