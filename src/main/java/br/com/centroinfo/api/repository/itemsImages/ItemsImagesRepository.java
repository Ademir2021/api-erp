package br.com.centroinfo.api.repository.itemsImages;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.centroinfo.api.entities.items.images.ItemsImages;

public interface ItemsImagesRepository extends JpaRepository<ItemsImages, Long> {
     List<ItemsImages> findByItemId(Long itemId);

}
