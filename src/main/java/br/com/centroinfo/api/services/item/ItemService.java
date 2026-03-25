package br.com.centroinfo.api.services.item;

import br.com.centroinfo.api.dtos.itemDTO.ItemDTO;
import br.com.centroinfo.api.entities.items.item.Item;
import br.com.centroinfo.api.repository.item.ItemRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    /**
     * @param item
     * @param itemDTO
     */
    private void mapItemFields(Item item, ItemDTO itemDTO) {
        item.setName(itemDTO.getName());
        item.setPriceMax(itemDTO.getPriceMax());
        item.setPriceMin(itemDTO.getPriceMin());
        item.setBrand(itemDTO.getBrand());
        item.setSubGroup(itemDTO.getSubGroup());
        item.setBarCode(itemDTO.getBarCode());
        item.setImagem(itemDTO.getImagem());
        item.setSubGroup(itemDTO.getSubGroup());
        item.setTaxGroup(itemDTO.getTaxGroup());
        item.setTypeItem(itemDTO.getTypeItem());
        item.setItemClass(itemDTO.getItemClass());
        item.setUnitMeasure(itemDTO.getUnitMeasure());
    }

    public List<Item> create(ItemDTO itemDTO) {
        Item item = new Item();
        item.setCreatedAt(LocalDateTime.now());
        mapItemFields(item, itemDTO);
        itemRepository.save(item);
        return list();
    }

    public List<Item> list() {
        return itemRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    // Método para buscar itens por nome
    public List<Item> searchItemsByName(String name) {
        if (name != null && !name.isEmpty()) {
            return itemRepository.findByNameContainingIgnoreCase(name);
        }
        return list();
    }

    public List<Item> update(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setCreatedAt(itemDTO.getCreatedAt());
        item.setUpdatedAt(LocalDateTime.now());
        mapItemFields(item, itemDTO);
        itemRepository.save(item);
        return list();
    }

    public List<Item> delete(Long id) {
        itemRepository.deleteById(id);
        return list();
    }
}
