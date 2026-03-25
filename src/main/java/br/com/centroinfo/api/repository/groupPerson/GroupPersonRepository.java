package br.com.centroinfo.api.repository.groupPerson;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.centroinfo.api.entities.persons.group_person.GroupPerson;

public interface GroupPersonRepository extends JpaRepository<GroupPerson, Long> {

}
