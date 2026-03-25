package br.com.centroinfo.api.services.groupPerson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centroinfo.api.entities.persons.group_person.GroupPerson;
import br.com.centroinfo.api.repository.groupPerson.GroupPersonRepository;

@Service
public class GroupPersonServices {

    @Autowired
    GroupPersonRepository groupPersonRepository;

    public List<GroupPerson> list() {
        return groupPersonRepository.findAll();
    }
}
