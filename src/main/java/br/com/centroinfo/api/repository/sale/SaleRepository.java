package br.com.centroinfo.api.repository.sale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.centroinfo.api.dtos.saleDTO.SaleResponseDTO;
import br.com.centroinfo.api.entities.sales.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("""
                SELECT new br.com.centroinfo.api.dtos.saleDTO.SaleResponseDTO(
                    s.id,
                    s.issueDate,

                    b.id,
                    b.name,

                    u.id,
                    u.login,

                    p.id,
                    p.name,

                    op.id,
                    op.description,
                    op.cfop,

                    s.totalSale,
                    s.discount,
                    s.totalNote
                )
                FROM Sale s
                JOIN s.branch b
                JOIN s.user u
                JOIN s.person p
                JOIN s.operationSale op
            """)
    List<SaleResponseDTO> listAllSales();
}
