package br.com.centroinfo.api.dtos.stateDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateDTO {
    private Long id;
    private String name;
    private String acronym; // Ex: SP para São Paulo, RJ para Rio de Janeiro

}
