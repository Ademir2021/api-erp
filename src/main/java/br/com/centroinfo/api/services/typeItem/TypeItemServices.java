package br.com.centroinfo.api.services.typeItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centroinfo.api.dtos.typeItemDTO.TypeItemDTO;
import br.com.centroinfo.api.entities.items.typeItem.TypeItem;
import br.com.centroinfo.api.repository.typeItem.TypeItemRepository;

@Service
public class TypeItemServices {

    @Autowired
    TypeItemRepository typeItemRepository;

    public TypeItem create(TypeItemDTO typeItemDTO) {
        TypeItem typeItem = new TypeItem();
        typeItem.setName(typeItemDTO.getName());
        typeItemRepository.save(typeItem);
        return typeItem;

    }

    public List<TypeItem> list() {
        return typeItemRepository.findAll();
    }

    public TypeItem update(TypeItemDTO typeItemDTO) {
        TypeItem typeItem = new TypeItem();
        typeItem.setId(typeItemDTO.getId());
        typeItem.setName(typeItemDTO.getName());
        typeItemRepository.save(typeItem);
        return typeItem;

    }

}
