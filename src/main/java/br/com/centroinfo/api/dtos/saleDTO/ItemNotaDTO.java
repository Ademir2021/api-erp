package br.com.centroinfo.api.dtos.saleDTO;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class ItemNotaDTO {
    private Long id;
    private String item;
    private BigDecimal quantity = BigDecimal.ZERO;
    private BigDecimal unitPrice = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;
    private String barCode;
    private String sector;
    private String marca;

    public ItemNotaDTO(
            Long id,
            String item,
            BigDecimal quantity,
            BigDecimal unitPrice,
            String barCode,
            String setcor,
            String marca) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = unitPrice.multiply(quantity) != null ? unitPrice.multiply(quantity) : BigDecimal.ZERO;
        this.barCode = barCode;
        this.sector = setcor;
        this.marca = marca;
    }
}
