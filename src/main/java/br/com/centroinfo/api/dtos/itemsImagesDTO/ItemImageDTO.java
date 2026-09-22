package br.com.centroinfo.api.dtos.itemsImagesDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ItemImageDTO {
    private Long id;
    private String fileName;
    private String filePath;
    private Long idItem;
}
