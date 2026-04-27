package br.com.centroinfo.api.dtos.cityDTO;

import br.com.centroinfo.api.entities.address.country.Country;
import br.com.centroinfo.api.entities.address.state.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDTO {
    private Long id;
    private String name;
    private String codeIbge;
    private State state;
    private Country country;
}
