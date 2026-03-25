package br.com.centroinfo.api.dtos.saleDTO;

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
    private Double totalSale;
    private Double discount;
    private Double totalNote;

    public NotaDTO(Long saleId, LocalDateTime issueDate,
    String branchName, String userName, String street,
    String personName, List<ItemNotaDTO> items, Double totalSale,
    Double discount, Double totalNote) {
        this.saleId = saleId;
        this.issueDate = issueDate;
        this.branchName = branchName;
        this.userName = userName;
        this.personName = personName;
        this.items = items;
        this.totalSale = totalSale;
        this.discount = discount;
        this.totalNote = totalNote;
        this.street = street;
    }
}

