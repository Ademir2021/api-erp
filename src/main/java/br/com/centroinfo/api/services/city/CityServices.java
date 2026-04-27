package br.com.centroinfo.api.services.city;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centroinfo.api.dtos.cityDTO.CityDTO;
import br.com.centroinfo.api.entities.address.city.City;
import br.com.centroinfo.api.repository.city.CityRepository;

@Service
public class CityServices {

    @Autowired
    CityRepository cityRepository;

    public City create(CityDTO cityDTO) {
        City city = new City();
        city.setName(cityDTO.getName());
        city.setCodeIbge(cityDTO.getCodeIbge());
        city.setCountry(cityDTO.getCountry());
        city.setState(cityDTO.getState());
        cityRepository.save(city);
        return city;
    }

    public List<City> list() {
        return cityRepository.findAll();
    }

    public City update(CityDTO cityDTO) {
        City city = new City();
        city.setId(cityDTO.getId());
        city.setName(cityDTO.getName());
        city.setCodeIbge(cityDTO.getCodeIbge());
        city.setCountry(cityDTO.getCountry());
        city.setState(cityDTO.getState());
        cityRepository.save(city);
        return city;
    }

}
