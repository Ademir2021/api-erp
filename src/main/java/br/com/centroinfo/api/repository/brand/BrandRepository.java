package br.com.centroinfo.api.repository.brand;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.centroinfo.api.entities.items.brand.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long>{}
