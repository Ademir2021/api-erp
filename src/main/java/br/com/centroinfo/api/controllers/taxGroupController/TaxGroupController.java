package br.com.centroinfo.api.controllers.taxGroupController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.centroinfo.api.entities.items.taxGroup.TaxGroup;
import br.com.centroinfo.api.services.taxGroup.TaxGroupServices;

@RestController
@RequestMapping("")
public class TaxGroupController {

    @Autowired
    private TaxGroupServices taxGroupServices;

    @GetMapping("tax_groups")
    public List<TaxGroup> list(){
        return taxGroupServices.list();
    }

}
