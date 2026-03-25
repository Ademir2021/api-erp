package br.com.centroinfo.api.services.subGroup;

import br.com.centroinfo.api.dtos.subGroupDTO.SubGroupDTO;
import br.com.centroinfo.api.entities.items.subGroup.SubGroup;
import br.com.centroinfo.api.repository.subGroup.SubGroupRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubGroupService {

    @Autowired
    private SubGroupRepository subGroupRepository;

    public List<SubGroup> create(SubGroupDTO subGroupDTO) {
        SubGroup subGroup = new SubGroup();
        subGroup.setName(subGroupDTO.name);
        subGroupRepository.save(subGroup);
        return list();
    }

    public List<SubGroup> list() {
        return subGroupRepository.findAll();
    }

    public List<SubGroup> update(SubGroupDTO subGroupDTO) {
        SubGroup subGroup = new SubGroup();
        subGroup.setId(subGroupDTO.id);
        subGroup.setName(subGroupDTO.name);
        subGroupRepository.save(subGroup);
        return list();
    }

    public List<SubGroup> delete(Long id) {
        subGroupRepository.deleteById((id));
        return list();
    }
}
