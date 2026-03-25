package br.com.centroinfo.api.repository.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.centroinfo.api.entities.items.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
     List<Item> findByNameContainingIgnoreCase(String name);
}
