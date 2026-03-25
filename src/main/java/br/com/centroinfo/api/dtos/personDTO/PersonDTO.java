package br.com.centroinfo.api.dtos.personDTO;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import br.com.centroinfo.api.dtos.addressDTO.AddressDTO;
import br.com.centroinfo.api.entities.branchs.Branch;
import br.com.centroinfo.api.entities.persons.Gender;
import br.com.centroinfo.api.entities.persons.TypePerson;
import br.com.centroinfo.api.entities.persons.group_person.GroupPerson;
import br.com.centroinfo.api.entities.users.User;

@Getter
@Setter
public class PersonDTO {
    private Long id;
    private LocalDateTime createdAt;
    private TypePerson typePerson;
    private GroupPerson groupPerson;
    private Branch branch;
    private User user;
    private String name;
    private int age;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String cpf;
    private String rg;
    private String email;
    private String phone;
    private String cnpj;
    private String inscricState;
    private AddressDTO address;
    public int calcAge() {
    return LocalDate.now().getYear() - dateOfBirth.getYear();
  }
}