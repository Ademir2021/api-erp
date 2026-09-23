package br.com.centroinfo.api.dtos.itemsImagesDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@AllArgsConstructor
@RequiredArgsConstructor
public class ItemImageDTO {
    private Long id;
    private String fileName;
    private String filePath;
    private Long idItem;
}
