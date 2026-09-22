package br.com.centroinfo.api.controllers.itemsImagesController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.centroinfo.api.dtos.itemsImagesDTO.ItemImageDTO;
import br.com.centroinfo.api.services.itemsImagesService.ItemImageService;

@RestController
@RequestMapping("")
public class ItemsImagesController {

    @Autowired
    ItemImageService itemImageService;

    @GetMapping("/images/item/{itemId}")
    public ResponseEntity<List<ItemImageDTO>> getImagesByItem(
            @PathVariable Long itemId) {

        return ResponseEntity.ok(
                itemImageService.findByItemId(itemId)
        );
    }

    @GetMapping("/images")
    public ResponseEntity<List<ItemImageDTO>> getImages() {

        return ResponseEntity.ok(
                itemImageService.findAll()
        );
    }

}
