package br.com.centroinfo.api.services.itemsImagesService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centroinfo.api.entities.items.images.ItemsImages;
import br.com.centroinfo.api.repository.itemsImages.ItemsImagesRepository;

@Service
public class ItemsImagesService {

    @Autowired
    private ItemsImagesRepository itemsImagesRepository;

    public List<ItemsImages> list() {
        return itemsImagesRepository.findAll();
    }
}
