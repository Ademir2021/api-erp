package br.com.centroinfo.api.services.itemsImagesService;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.centroinfo.api.dtos.itemsImagesDTO.ItemImageDTO;
import br.com.centroinfo.api.repository.itemsImages.ItemsImagesRepository;

@Service
public class ItemImageService {

    private final ItemsImagesRepository itemsImagesRepository;

    public ItemImageService(ItemsImagesRepository itemsImagesRepository) {
        this.itemsImagesRepository = itemsImagesRepository;
    }

    public List<ItemImageDTO> findByItemId(Long itemId) {

        return itemsImagesRepository.findByItemId(itemId)
                .stream()
                .map(image -> new ItemImageDTO(
                image.getId(),
                image.getFileName(),
                image.getFilePath(),
                image.getItem().getId()
        ))
                .toList();
    }

    public List<ItemImageDTO> findAll() {

        return itemsImagesRepository.findAll()
                .stream()
                .map(image -> new ItemImageDTO(
                image.getId(),
                image.getFileName(),
                image.getFilePath(),
                image.getItem().getId()
        ))
                .toList();
    }

}