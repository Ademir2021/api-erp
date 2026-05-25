package br.com.centroinfo.api.dtos.saleDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleResponseDTO {

    private Long id;
    private LocalDateTime issueDate;

    private BranchResponseDTO branch;
    private UserResponseDTO user;
    private PersonResponseDTO person;
    private OperationSaleResponseDTO operationSale;

    private BigDecimal totalSale;
    private BigDecimal discount;
    private BigDecimal totalNote;
    private Boolean cancel;

    public SaleResponseDTO(
            Long id,
            LocalDateTime issueDate,

            Long bId,
            String bName,

            Long uId,
            String uLogin,

            Long pId,
            String pName,

            Long opId,
            String opDescription,
            String opCfop,

            BigDecimal totalSale,
            BigDecimal discount,
            BigDecimal totalNote,
            Boolean cancel
    ) {
        this.id = id;
        this.issueDate = issueDate;

        this.branch = new BranchResponseDTO(bId, bName);
        this.user = new UserResponseDTO(uId, uLogin);
        this.person = new PersonResponseDTO(pId, pName);
        this.operationSale = new OperationSaleResponseDTO(opId, opDescription, opCfop);

        this.totalSale = totalSale;
        this.discount = discount;
        this.totalNote = totalNote;
        this.cancel = cancel;
    }

    private final record BranchResponseDTO(Long id, String name) {}

    private final record UserResponseDTO(Long id, String login) {}

    private final record PersonResponseDTO(Long id, String name) {}

    private final record OperationSaleResponseDTO(
            Long id,
            String description,
            String cfop
    ) {}
}