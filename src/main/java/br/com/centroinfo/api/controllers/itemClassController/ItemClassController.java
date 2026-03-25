package br.com.centroinfo.api.controllers.itemClassController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.centroinfo.api.entities.items.itemClass.ItemClass;
import br.com.centroinfo.api.services.itemClass.ItemClassService;

@RestController
@RequestMapping("")
public class ItemClassController {

    @Autowired
    ItemClassService itemClassService;

    @GetMapping("/items_classes")
    List<ItemClass> list() {
        return itemClassService.list();
    }

}
