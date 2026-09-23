package br.com.centroinfo.api.services.itemsImagesService;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.centroinfo.api.dtos.itemsImagesDTO.ItemImageDTO;
import br.com.centroinfo.api.entities.items.images.ItemsImages;
import br.com.centroinfo.api.entities.items.item.Item;
import br.com.centroinfo.api.repository.itemsImages.ItemsImagesRepository;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class ItemImageService {

    private final ItemsImagesRepository itemsImagesRepository;

    public ItemImageService(
            ItemsImagesRepository itemsImagesRepository) {
        this.itemsImagesRepository = itemsImagesRepository;
    }

    public List<ItemImageDTO> findByItemId(Long itemId) {

        return itemsImagesRepository.findByItemId(itemId)
                .stream()
                .map(image -> new ItemImageDTO(
                        image.getId(),
                        image.getFileName(),
                        image.getFilePath(),
                        image.getItem().getId()))
                .toList();
    }

    public List<ItemImageDTO> findAll() {
        return itemsImagesRepository.findAll()
                .stream()
                .map(image -> new ItemImageDTO(
                        image.getId(),
                        image.getFileName(),
                        image.getFilePath(),
                        image.getItem().getId()))
                .toList();
    };

    public void updateImages(
            Item item,
            List<MultipartFile> images) {
        if (images == null || images.isEmpty()) {
            return;
        }

        for (MultipartFile file : images) {
            if (file == null || file.isEmpty()) {
                continue;
            }
            try {
                String originalFileName = file.getOriginalFilename();
                Path directory = Paths.get("imgs", "items");
                Files.createDirectories(directory);
                ItemsImages image = new ItemsImages();
                image.setFileName(originalFileName);
                image.setFilePath("/imgs/items/");
                image.setItem(item);
                itemsImagesRepository.save(image);
            } catch (IOException e) {
                throw new RuntimeException(
                        "Erro ao salvar imagem do item", e);
            }
        }
    }

}
