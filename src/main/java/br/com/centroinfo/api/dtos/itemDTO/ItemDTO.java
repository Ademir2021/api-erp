package br.com.centroinfo.api.dtos.itemDTO;

import java.time.LocalDateTime;

import br.com.centroinfo.api.entities.items.brand.Brand;
import br.com.centroinfo.api.entities.items.itemClass.ItemClass;
import br.com.centroinfo.api.entities.items.subGroup.SubGroup;
import br.com.centroinfo.api.entities.items.taxGroup.TaxGroup;
import br.com.centroinfo.api.entities.items.typeItem.TypeItem;
import br.com.centroinfo.api.entities.items.unitMeasure.UnitMeasure;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {
  private Long id;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String name;
  private double priceMax;
  private double priceMin;
  private String barCode;
  private String imagem;
  private Brand brand;
  private SubGroup subGroup;
  private TaxGroup taxGroup;
  private TypeItem typeItem;
  private ItemClass itemClass;
  private UnitMeasure unitMeasure;
}