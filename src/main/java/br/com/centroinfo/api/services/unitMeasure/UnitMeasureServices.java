package br.com.centroinfo.api.services.unitMeasure;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centroinfo.api.dtos.unitMeasureDTO.UnitMeasureDTO;
import br.com.centroinfo.api.entities.items.unitMeasure.UnitMeasure;
import br.com.centroinfo.api.repository.unitMeasure.UnitMeasureRepository;

@Service
public class UnitMeasureServices {

    @Autowired
    UnitMeasureRepository unitMeasureRepository;

    public UnitMeasure create(UnitMeasureDTO unitMeasureDTO) {
        UnitMeasure unitMeasure = new UnitMeasure();
        unitMeasure.setName(unitMeasureDTO.getName());
        unitMeasureRepository.save(unitMeasure);
        return unitMeasure;
    }

    public List<UnitMeasure> list() {
        return unitMeasureRepository.findAll();
    }

    public UnitMeasure update(UnitMeasureDTO unitMeasureDTO) {
        UnitMeasure unitMeasure = new UnitMeasure();
        unitMeasure.setId(unitMeasureDTO.getId());
        unitMeasure.setName(unitMeasureDTO.getName());
        unitMeasureRepository.save(unitMeasure);
        return unitMeasure;
    }

}
