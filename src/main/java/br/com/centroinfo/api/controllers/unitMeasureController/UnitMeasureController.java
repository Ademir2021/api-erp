package br.com.centroinfo.api.controllers.unitMeasureController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.centroinfo.api.entities.items.unitMeasure.UnitMeasure;
import br.com.centroinfo.api.services.unitMeasure.UnitMeasureServices;

@RestController
@RequestMapping("")
public class UnitMeasureController {

    @Autowired
    UnitMeasureServices unitMeasureServices;

    @GetMapping("/unit_measures")
    List<UnitMeasure> list() {
        return unitMeasureServices.list();
    }

}
