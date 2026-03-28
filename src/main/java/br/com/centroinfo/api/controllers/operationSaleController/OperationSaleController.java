package br.com.centroinfo.api.controllers.operationSaleController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.centroinfo.api.entities.sales.OperationSale;
import br.com.centroinfo.api.services.operationSale.OperationSaleServices;

@RestController
@RequestMapping("")
public class OperationSaleController {

    @Autowired
    OperationSaleServices operationSaleServices;

    @GetMapping("/operations_sale")
    List<OperationSale> list(){
        return operationSaleServices.list();
    }

}
