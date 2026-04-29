package br.com.centroinfo.api.repository.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.centroinfo.api.entities.items.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
     List<Item> findByNameContainingIgnoreCase(String name);

     @Query("SELECT i FROM Item i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :term, '%')) OR i.barCode = :term")
     List<Item> searchByNameOrBarcode(@Param("term") String term);
}
