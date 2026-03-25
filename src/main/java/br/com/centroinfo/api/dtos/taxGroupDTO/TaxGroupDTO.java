package br.com.centroinfo.api.dtos.taxGroupDTO;

import br.com.centroinfo.api.entities.items.taxationTable.TaxationTable;

public class TaxGroupDTO {
    public Long id;
    public String name;
    public TaxationTable taxationTable;
}
