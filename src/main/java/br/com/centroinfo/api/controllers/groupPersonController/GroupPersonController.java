package br.com.centroinfo.api.controllers.groupPersonController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.centroinfo.api.entities.persons.group_person.GroupPerson;
import br.com.centroinfo.api.services.groupPerson.GroupPersonServices;

@RestController
@RequestMapping("")
public class GroupPersonController {

    @Autowired
    GroupPersonServices groupPersonServices;

    @GetMapping("group_persons")
    List<GroupPerson> list() {
        return groupPersonServices.list();
    }
}
