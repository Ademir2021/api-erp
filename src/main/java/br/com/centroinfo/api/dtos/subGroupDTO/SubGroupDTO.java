package br.com.centroinfo.api.dtos.subGroupDTO;

import br.com.centroinfo.api.entities.items.group.Group;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
 public class SubGroupDTO {
 Long id;
 String name;
 Group group;
}
