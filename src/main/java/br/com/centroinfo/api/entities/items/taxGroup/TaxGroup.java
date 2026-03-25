package br.com.centroinfo.api.entities.items.taxGroup;

import br.com.centroinfo.api.entities.items.taxationTable.TaxationTable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tax_groups")
@Entity
@Getter
@Setter
@NoArgsConstructor

public class TaxGroup { // Grupo de tributação
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name="taxation_table_id")
    private TaxationTable taxationTable;
}

