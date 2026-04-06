package br.com.centroinfo.api.services.sale;

import br.com.centroinfo.api.dtos.saleDTO.ItemNotaDTO;
import br.com.centroinfo.api.dtos.saleDTO.NotaDTO;
import br.com.centroinfo.api.entities.sales.Sale;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class NotaService {
    
    public NotaDTO gerarNota(Sale sale) {
        List<ItemNotaDTO> itemDTOs = sale.getItemsSale().stream().map(item -> {
            Long id = item.getItem().getId();
            String item_ = item.getItem().getName();
            BigDecimal amount = item.getAmount();
            BigDecimal price = item.getPrice();
            String barCode = item.getItem().getBarCode();
            String sector = item.getItem().getSubGroup().getName();
            String brand = item.getItem().getBrand().getName();
            return new ItemNotaDTO(id, item_, amount, price, barCode, sector, brand);
        }).collect(Collectors.toList());

        return new NotaDTO(
                sale.getId(),
                sale.getIssueDate(),
                sale.getBranch().getName(),
                sale.getUser().getLogin(),
                sale.getPerson().getName(),
                sale.getPerson().getAddress().getStreet(),
                itemDTOs,
                sale.getTotalSale(),
                sale.getDiscount(),
                sale.getTotalNote()
        );
    }
}

