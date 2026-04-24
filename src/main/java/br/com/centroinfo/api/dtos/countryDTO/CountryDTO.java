
package br.com.centroinfo.api.dtos.countryDTO;

import java.util.List;

import br.com.centroinfo.api.entities.address.city.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDTO {
    Long id;
    String name;
    String acronym;
    String ddi;
    String codeCountry;
    String codeRevenue;
    List<City> cities;
}
