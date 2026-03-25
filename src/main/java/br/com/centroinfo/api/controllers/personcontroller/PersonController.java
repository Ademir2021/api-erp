package br.com.centroinfo.api.controllers.personcontroller;

import br.com.centroinfo.api.dtos.personDTO.PersonDTO;
import br.com.centroinfo.api.dtos.personDTO.PersonResponseDTO;
import br.com.centroinfo.api.entities.persons.Person;
import br.com.centroinfo.api.services.person.PersonService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping("/person")
    public Person create(@RequestBody PersonDTO personDTO) {
        return personService.save(personDTO);
    }

    @GetMapping("/persons")
    public List<PersonResponseDTO> list() {
        return personService.list();
    }

    @GetMapping("/search_person")
    public List<PersonResponseDTO> searchPersons(@RequestParam String name) {
        return personService.getPersonsByName(name);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody PersonDTO personDTO) {
        personDTO.setId(id);
        try {
            Person person = personService.update(personDTO);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Pessoa atualizada com sucesso",
                    "id", person.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Erro ao atualizar pessoa",
                    "details", e.getMessage()));
        }
    }

    @DeleteMapping("/person/{id}")
    public List<PersonResponseDTO> delete(@PathVariable("id") Long id) {
        return personService.delete(id);
    }

}
