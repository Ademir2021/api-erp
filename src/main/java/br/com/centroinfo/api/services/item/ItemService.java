package br.com.centroinfo.api.services.item;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.centroinfo.api.dtos.itemDTO.ItemDTO;
import br.com.centroinfo.api.entities.items.images.ItemsImages;
import br.com.centroinfo.api.entities.items.item.Item;
import br.com.centroinfo.api.repository.item.ItemRepository;
import br.com.centroinfo.api.services.itemsImagesService.ItemImageService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired 
    private ItemImageService itemImageService;

    /**
     * @param item
     * @param itemDTO
     */
    private void mapItemFields(Item item, ItemDTO itemDTO) {
        item.setName(itemDTO.getName());
        item.setPriceMax(itemDTO.getPriceMax());
        item.setPriceMin(itemDTO.getPriceMin());
        item.setBrand(itemDTO.getBrand());
        item.setBarCode(itemDTO.getBarCode());
        item.setImagem(itemDTO.getImagem());
        item.setSubGroup(itemDTO.getSubGroup());
        item.setTaxGroup(itemDTO.getTaxGroup());
        item.setTypeItem(itemDTO.getTypeItem());
        item.setItemClass(itemDTO.getItemClass());
        item.setUnitMeasure(itemDTO.getUnitMeasure());
    }

    public Item create(ItemDTO itemDTO, List<MultipartFile> images) {

        Item item = new Item();
        item.setCreatedAt(LocalDateTime.now());
        mapItemFields(item, itemDTO);

        if (images != null && !images.isEmpty()) {
            for (MultipartFile image : images) {
                ItemsImages itemImage = new ItemsImages();
                itemImage.setFileName(image.getOriginalFilename());
                /// depois definimos o caminho onde será salva
                itemImage.setFilePath(
                        "/imgs/items/" + image.getOriginalFilename()
                );
                itemImage.setItem(item);
                item.getImages().add(itemImage);
            }
        }
        return itemRepository.save(item);
    }

    public List<Item> list() {
        return itemRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

// Método para buscar itens por nome, codigo de barras ou id
    public List<Item> searchItems(String term) {
        if (term == null || term.trim().isEmpty()) {
            return List.of();
        };
        term = term.trim();
// Se for número, tenta buscar por ID também
        if (term.matches("\\d+")) {
            Long id = Long.valueOf(term);
            List<Item> result = new ArrayList<>();
            result.addAll(itemRepository.searchById(id));
            result.addAll(itemRepository.searchByNameOrBarcode(term));
            return result;
        }
        return itemRepository.searchByNameOrBarcode(term);
    }

@Transactional
public Item update(ItemDTO itemDTO, List<MultipartFile> images) {

    Item item = itemRepository.findById(itemDTO.getId())
            .orElseThrow(() ->
                    new RuntimeException(
                            "Item não encontrado: " + itemDTO.getId()
                    )
            );

    item.setCreatedAt(item.getCreatedAt());
    item.setUpdatedAt(LocalDateTime.now());
    mapItemFields(item, itemDTO);
    Item updatedItem = itemRepository.save(item);

    // Atualiza as imagens
    if (images != null && !images.isEmpty()) {
        itemImageService.updateImages(item, images);
    }

    return updatedItem;
}

    public List<Item> delete(Long id) {
        itemRepository.deleteById(id);
        return list();
    }
}
