package br.com.centroinfo.api.repository.typeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.centroinfo.api.entities.items.typeItem.TypeItem;


public interface TypeItemRepository extends JpaRepository<TypeItem, Long> {}