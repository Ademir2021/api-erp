package br.com.centroinfo.api.dtos.operationSaleDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationSaleDTO {
    private Long id;
    private String description;
    private String type;
    private Boolean controlsStock;
    private Boolean generateFinancial;
    private Boolean allowDiscount;
    private Boolean updateCost;
    private Boolean finalConsumer;
    private Boolean requiresInvoice;
    private Boolean isReturn;
    private String cfop;
    private String defaultNature;
    private Boolean active;
}
