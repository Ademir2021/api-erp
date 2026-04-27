package br.com.centroinfo.api.repository.city;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.centroinfo.api.entities.address.city.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
