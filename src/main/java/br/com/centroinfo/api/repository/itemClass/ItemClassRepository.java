package br.com.centroinfo.api.repository.itemClass;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.centroinfo.api.entities.items.itemClass.ItemClass;

public interface ItemClassRepository extends JpaRepository<ItemClass, Long> {

}
