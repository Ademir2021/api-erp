package br.com.centroinfo.api.repository.taxGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.centroinfo.api.entities.items.taxGroup.TaxGroup;

public interface TaxGroupRepository extends JpaRepository<TaxGroup, Long> {}
