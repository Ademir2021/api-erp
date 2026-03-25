package br.com.centroinfo.api.repository.sale;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.centroinfo.api.entities.sales.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {}
