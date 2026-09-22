package br.com.centroinfo.api.controllers.itemsImagesController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.centroinfo.api.dtos.itemsImagesDTO.ItemImageDTO;
import br.com.centroinfo.api.repository.itemsImages.ItemsImagesRepository;

@RestController
@RequestMapping("")
public class ItemsImagesController {

@Autowired
ItemsImagesRepository itemsImagesRepository;

@GetMapping("/images")
public ResponseEntity<List<ItemImageDTO>> getImages() {

    List<ItemImageDTO> images = itemsImagesRepository.findAll()
        .stream()
        .map(image -> new ItemImageDTO(
            image.getId(),
            image.getFileName(),
            image.getFilePath(),
            image.getItem().getId()
        ))
        .toList();

    return ResponseEntity.ok(images);
}
}
