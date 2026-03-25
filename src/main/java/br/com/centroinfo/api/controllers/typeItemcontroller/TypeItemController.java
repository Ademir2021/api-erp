package br.com.centroinfo.api.controllers.typeItemcontroller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.centroinfo.api.entities.items.typeItem.TypeItem;
import br.com.centroinfo.api.services.typeItem.TypeItemServices;


@RestController
@RequestMapping("")
public class TypeItemController {

    @Autowired
    private TypeItemServices typeItemServices;

    @GetMapping("type_items")
    public List<TypeItem> list(){
        return typeItemServices.list();
    }

}

