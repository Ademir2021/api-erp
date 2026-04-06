package br.com.centroinfo.api.dtos.saleDTO;

import java.math.BigDecimal;

import br.com.centroinfo.api.entities.items.item.Item;
import lombok.Getter;

@Getter
public class ItemSaleDTO {
    private Item item;
    private BigDecimal amount = BigDecimal.ZERO;
    private BigDecimal price = BigDecimal.ZERO;
}