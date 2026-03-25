package br.com.centroinfo.api.dtos.saleDTO;

import br.com.centroinfo.api.entities.items.item.Item;
import lombok.Getter;

@Getter
public class ItemSaleDTO {
    private Item item;
    private Integer amount;
    private Double price;
}