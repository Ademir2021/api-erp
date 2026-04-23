package br.com.centroinfo.api.services.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centroinfo.api.dtos.groupDTO.GroupDTO;
import br.com.centroinfo.api.entities.items.group.Group;
import br.com.centroinfo.api.repository.group.GroupRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class GroupItemService {

    @Autowired
    GroupRepository groupRepository;

    public Group create (GroupDTO groupDTO){
        Group group = new Group();
        group.setName(groupDTO.getName());
        groupRepository.save(group);
        return group;
    }

    public List<Group> list (){
        return groupRepository.findAll();
    }

     public Group update (GroupDTO groupDTO){
        Group group = new Group();
        group.setId(groupDTO.getId());
        group.setName(groupDTO.getName());
        groupRepository.save(group);
        return group;
    } 

    public void delete(Long id) {
        groupRepository.deleteById(id);
    }
}
