package br.com.centroinfo.api.controllers.zipcodecontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.centroinfo.api.dtos.zipcodeDTO.ZipcodeDTO;
import br.com.centroinfo.api.services.zipcode.ZipcodeService;

@RestController
@RequestMapping("")
public class ZipcodeController {

    @Autowired
    private ZipcodeService zipcodeService;

    public ZipcodeController(ZipcodeService zipcodeService){
        this.zipcodeService = zipcodeService;
    }

    @GetMapping("/zipcodes")
    public List<ZipcodeDTO> list() {
        return zipcodeService.list();
    }

}
