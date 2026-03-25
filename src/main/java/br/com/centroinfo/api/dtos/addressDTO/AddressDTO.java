package br.com.centroinfo.api.dtos.addressDTO;

import br.com.centroinfo.api.entities.address.zipcode.ZipCode;
import br.com.centroinfo.api.entities.persons.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private Long id;
    private String street;
    private String number;
    private String neighborhood;
    private String complement;
    private ZipCode zipCode;
    private Person person;
}