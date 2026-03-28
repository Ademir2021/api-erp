package br.com.centroinfo.api.repository.operationSaleDTO;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.centroinfo.api.entities.sales.OperationSale;

public interface OperationSaleRepository extends JpaRepository<OperationSale, Long> {

}
