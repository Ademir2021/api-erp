package br.com.centroinfo.api.services.itemClass;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centroinfo.api.dtos.itemClassDTO.ItemClassDTO;
import br.com.centroinfo.api.entities.items.itemClass.ItemClass;
import br.com.centroinfo.api.repository.itemClass.ItemClassRepository;

@Service
public class ItemClassService {

    @Autowired
    ItemClassRepository itemClassRepository;

    public ItemClass create(ItemClassDTO itemClassDTO) {
        ItemClass itemClass = new ItemClass();
        itemClass.setName(itemClassDTO.getName());
        itemClassRepository.save(itemClass);
        return itemClass;
    };

    public List<ItemClass> list() {
        return itemClassRepository.findAll();
    };

    public ItemClass update(ItemClassDTO itemClassDTO) {

        ItemClass itemClass = new ItemClass();
        itemClass.setId(itemClassDTO.getId());
        itemClass.setName(itemClassDTO.getName());
        itemClassRepository.save(itemClass);
        return itemClass;
    }
}
