package br.com.centroinfo.api.services.unitMeasure;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.centroinfo.api.entities.items.unitMeasure.UnitMeasure;
import br.com.centroinfo.api.repository.unitMeasure.UnitMeasureRepository;

@Service
public class UnitMeasureServices {

    @Autowired
    UnitMeasureRepository unitMeasureRepository;

    public List<UnitMeasure> list() {
        return unitMeasureRepository.findAll();
    }

}
