package br.com.centroinfo.api.services.operationSale;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centroinfo.api.entities.sales.OperationSale;
import br.com.centroinfo.api.repository.operationSaleDTO.OperationSaleRepository;

@Service
public class OperationSaleServices {

    @Autowired
    OperationSaleRepository operationSaleRepository;

   public List<OperationSale> list(){
        return operationSaleRepository.findAll();
    }

}
