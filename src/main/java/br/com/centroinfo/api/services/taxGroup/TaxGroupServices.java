package br.com.centroinfo.api.services.taxGroup;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.centroinfo.api.entities.items.taxGroup.TaxGroup;
import br.com.centroinfo.api.repository.taxGroup.TaxGroupRepository;

@Service
public class TaxGroupServices {

    @Autowired
    private TaxGroupRepository taxGroupRepository;

    public List<TaxGroup> list (){
        return taxGroupRepository.findAll();
    }

}
