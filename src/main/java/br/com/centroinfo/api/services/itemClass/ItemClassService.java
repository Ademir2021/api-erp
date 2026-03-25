package br.com.centroinfo.api.services.itemClass;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.centroinfo.api.entities.items.itemClass.ItemClass;
import br.com.centroinfo.api.repository.itemClass.ItemClassRepository;

@Service
public class ItemClassService {

    @Autowired
    ItemClassRepository itemClassRepository;

   public List<ItemClass> list() {
        return itemClassRepository.findAll();
    }

}
