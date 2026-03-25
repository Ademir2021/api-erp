package br.com.centroinfo.api.entities.items.item;

import java.time.LocalDateTime;

import br.com.centroinfo.api.entities.items.brand.Brand;
import br.com.centroinfo.api.entities.items.itemClass.ItemClass;
import br.com.centroinfo.api.entities.items.subGroup.SubGroup;
import br.com.centroinfo.api.entities.items.taxGroup.TaxGroup;
import br.com.centroinfo.api.entities.items.typeItem.TypeItem;
import br.com.centroinfo.api.entities.items.unitMeasure.UnitMeasure;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "items")
@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    private String name;
    @Column(name = "price_max")
    private double priceMax;
    @Column(name = "price_min")
    private double priceMin;
    @Column(name = "bar_code")
    private String barCode;
    private String imagem;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "sub_group_id")
    private SubGroup subGroup;
    @ManyToOne
    @JoinColumn(name = "tax_group_id")
    TaxGroup taxGroup;
    @ManyToOne
    @JoinColumn(name = "type_item_id")
    private TypeItem typeItem;
    @ManyToOne
    @JoinColumn(name = "item_class_id")
    private ItemClass itemClass;
    @ManyToOne
    @JoinColumn(name = "unit_measure_id")
    private UnitMeasure unitMeasure;
}
