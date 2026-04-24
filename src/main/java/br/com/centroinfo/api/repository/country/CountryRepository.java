package br.com.centroinfo.api.repository.country;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.centroinfo.api.entities.address.country.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
