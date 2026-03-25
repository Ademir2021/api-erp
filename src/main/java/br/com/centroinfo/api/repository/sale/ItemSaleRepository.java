package br.com.centroinfo.api.repository.sale;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.centroinfo.api.entities.sales.ItemSale;

public interface ItemSaleRepository extends JpaRepository<ItemSale, Long> {}

