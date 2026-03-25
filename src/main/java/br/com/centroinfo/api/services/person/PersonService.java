package br.com.centroinfo.api.services.person;

import br.com.centroinfo.api.dtos.personDTO.PersonDTO;
import br.com.centroinfo.api.dtos.personDTO.PersonResponseDTO;
import br.com.centroinfo.api.entities.address.address.Address;
import br.com.centroinfo.api.entities.persons.Person;

import br.com.centroinfo.api.repository.person.PersonRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    /**
     * @param person
     * @param personDTO
     */
    private void mapPersonFields(Person person, PersonDTO personDTO) {
        person.setTypePerson(personDTO.getTypePerson());
        person.setGroupPerson(personDTO.getGroupPerson());
        person.setBranch(personDTO.getBranch());
        person.setUser(personDTO.getUser());
        person.setName(personDTO.getName());
        person.setDateOfBirth(personDTO.getDateOfBirth());
        person.setAge(personDTO.calcAge());
        person.setGender(personDTO.getGender());
        person.setCpf(personDTO.getCpf());
        person.setRg(personDTO.getRg());
        person.setEmail(personDTO.getEmail());
        person.setPhone(personDTO.getPhone());
        person.setCnpj(personDTO.getCnpj());
        person.setInscricState(personDTO.getInscricState());
    }

    /**
     * @param address
     * @param addressDTO
     */
    private void mapAddressFields(Address address, PersonDTO addressDTO) {
        address.setZipCode(addressDTO.getAddress().getZipCode());
        address.setStreet(addressDTO.getAddress().getStreet());
        address.setNumber(addressDTO.getAddress().getNumber());
        address.setNeighborhood(addressDTO.getAddress().getNeighborhood());
        address.setComplement(addressDTO.getAddress().getComplement());
    }

    /**
     * @param personDTO
     * @return
     */
    public Person save(PersonDTO personDTO) {

        if (personDTO == null) {
            throw new RuntimeException("Dados não informados");
        }

        Person pers = new Person();
        pers.setCreatedAt(LocalDateTime.now());
        mapPersonFields(pers, personDTO);

        Address address = new Address();
        mapAddressFields(address, personDTO);
        pers.setAddress(address);

        return personRepository.save(pers);
    }

    public List<PersonResponseDTO> list() {
        return personRepository.findPersonsAll();
    }

    public List<PersonResponseDTO> getPersonsByName(String name) {
        return personRepository.findPersonsByName(name);
    }

    /**
     * @param personDTO
     * @return
     */
    public Person update(PersonDTO personDTO) {

        if (personDTO == null) {
            throw new RuntimeException("Dados não informados");
        }

        Person pers = new Person();
        pers.setId(personDTO.getId());
        pers.setCreatedAt(personDTO.getCreatedAt());
        pers.setUpdatedAt(LocalDateTime.now());
        mapPersonFields(pers, personDTO);

        Address address = new Address();
        address.setId(personDTO.getAddress().getId());
        mapAddressFields(address, personDTO);
        pers.setAddress(address);

        return personRepository.save(pers);
    };

    public List<PersonResponseDTO> delete(Long id) {
        personRepository.deleteById(id);
        return list();
    }
}
