package br.com.centroinfo.api.services.itemsImagesService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

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
                String extension = "";
                if (originalFileName != null
                        && originalFileName.contains(".")) {
                    extension = originalFileName.substring(
                            originalFileName.lastIndexOf("."));
                }
                String fileName = UUID.randomUUID() + extension;
                Path directory = Paths.get("imgs", "items",
                        String.valueOf(item.getId()));
                Files.createDirectories(directory);
                Path filePath = directory.resolve(fileName);
                Files.write(
                        filePath,
                        file.getBytes());
                ItemsImages image = new ItemsImages();
                image.setFileName(originalFileName);
                image.setFilePath(filePath.toString());
                image.setItem(item);
                itemsImagesRepository.save(image);
            } catch (IOException e) {
                throw new RuntimeException(
                        "Erro ao salvar imagem do item", e);
            }
        }
    }

}
