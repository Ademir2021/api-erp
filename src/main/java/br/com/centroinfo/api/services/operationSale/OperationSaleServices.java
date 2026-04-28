package br.com.centroinfo.api.services.operationSale;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centroinfo.api.dtos.operationSaleDTO.OperationSaleDTO;
import br.com.centroinfo.api.entities.sales.OperationSale;
import br.com.centroinfo.api.repository.operationSale.OperationSaleRepository;

@Service
public class OperationSaleServices {

    @Autowired
    OperationSaleRepository operationSaleRepository;

    public OperationSale create(OperationSaleDTO operationSaleDTO) {
        OperationSale operationSale = new OperationSale();
        operationSale.setDescription(operationSaleDTO.getDescription());
        operationSale.setType(operationSaleDTO.getType());
        operationSale.setControlsStock(operationSaleDTO.getControlsStock());
        operationSale.setGenerateFinancial(operationSaleDTO.getGenerateFinancial());
        operationSale.setAllowDiscount(operationSaleDTO.getAllowDiscount());
        operationSale.setUpdateCost(operationSaleDTO.getUpdateCost());
        operationSale.setFinalConsumer(operationSaleDTO.getFinalConsumer());
        operationSale.setRequiresInvoice(operationSaleDTO.getRequiresInvoice());
        operationSale.setIsReturn(operationSaleDTO.getIsReturn());
        operationSale.setCfop(operationSaleDTO.getCfop());
        operationSale.setDefaultNature(operationSaleDTO.getDefaultNature());
        operationSale.setActive(operationSaleDTO.getActive());
        operationSaleRepository.save(operationSale);
        return operationSale;
    }

    public List<OperationSale> list() {
        return operationSaleRepository.findAll();
    }

    public OperationSale update(OperationSaleDTO operationSaleDTO) {
        OperationSale operationSale = new OperationSale();
        operationSale.setId(operationSaleDTO.getId());
        operationSale.setDescription(operationSaleDTO.getDescription());
        operationSale.setType(operationSaleDTO.getType());
        operationSale.setControlsStock(operationSaleDTO.getControlsStock());
        operationSale.setGenerateFinancial(operationSaleDTO.getGenerateFinancial());
        operationSale.setAllowDiscount(operationSaleDTO.getAllowDiscount());
        operationSale.setUpdateCost(operationSaleDTO.getUpdateCost());
        operationSale.setFinalConsumer(operationSaleDTO.getFinalConsumer());
        operationSale.setRequiresInvoice(operationSaleDTO.getRequiresInvoice());
        operationSale.setIsReturn(operationSaleDTO.getIsReturn());
        operationSale.setCfop(operationSaleDTO.getCfop());
        operationSale.setDefaultNature(operationSaleDTO.getDefaultNature());
        operationSale.setActive(operationSaleDTO.getActive());
        operationSaleRepository.save(operationSale);
        return operationSale;
    }
}
