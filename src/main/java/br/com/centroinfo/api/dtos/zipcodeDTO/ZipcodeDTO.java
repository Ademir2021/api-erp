package br.com.centroinfo.api.dtos.zipcodeDTO;

import br.com.centroinfo.api.entities.address.city.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZipcodeDTO {
    private Long id;
    private String code;
    private City city;

    public ZipcodeDTO(Long id, String code) {
        this.id = id;
        this.code = code;
    }
}
