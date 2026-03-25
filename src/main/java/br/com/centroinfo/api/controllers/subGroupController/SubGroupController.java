package br.com.centroinfo.api.controllers.subGroupController;

import br.com.centroinfo.api.dtos.subGroupDTO.SubGroupDTO;
import br.com.centroinfo.api.entities.items.subGroup.SubGroup;
import br.com.centroinfo.api.services.subGroup.SubGroupService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class SubGroupController {

    @Autowired
    private SubGroupService sectorService;

    public SubGroupController(SubGroupService sectorService) {
        this.sectorService = sectorService;
    }

    @PostMapping("/sub_group")
    public List<SubGroup> create(@RequestBody @Validated SubGroupDTO sectorDTO) {
        sectorService.create(sectorDTO);
        return sectorService.list();
    }

    @GetMapping("/sub_groups")
    List<SubGroup> list() {
        return sectorService.list();
    }

    @PutMapping("/sub_groups")
    List<SubGroup> update(@RequestBody @Validated SubGroupDTO sectorDTO) {
        return sectorService.update(sectorDTO);
    }

    @DeleteMapping("/sub_groups/{id}")
    List<SubGroup> delete(@PathVariable("id") Long id) {
        return sectorService.delete(id);
    }

}
