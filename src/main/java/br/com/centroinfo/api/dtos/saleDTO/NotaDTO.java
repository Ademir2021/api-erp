package br.com.centroinfo.api.dtos.saleDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;

@Getter
public class NotaDTO {
    private Long saleId;
    private LocalDateTime issueDate;
    private String branchName;
    private String userName;
    private String personName;
    private String street;
    private List<ItemNotaDTO> items;
    private BigDecimal totalSale;
    private BigDecimal discount;
    private BigDecimal totalNote;

    public NotaDTO(
            Long saleId,
            LocalDateTime issueDate,
            String branchName,
            String userName,
            String personName,
            String street,
            List<ItemNotaDTO> items,
            BigDecimal totalSale,
            BigDecimal discount,
            BigDecimal totalNote) {
        this.saleId = saleId;
        this.issueDate = issueDate;
        this.branchName = branchName;
        this.userName = userName;
        this.street = street;
        this.personName = personName;
        this.items = items;
        this.totalSale = totalSale;
        this.discount = discount;
        this.totalNote = totalNote;
    }
}
