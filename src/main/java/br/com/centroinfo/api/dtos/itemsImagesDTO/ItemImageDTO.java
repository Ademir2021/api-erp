package br.com.centroinfo.api.dtos.itemsImagesDTO;

import lombok.Getter;

@Getter
public class ItemImageDTO {

    private Long id;
    private String fileName;
    private String filePath;
    private Long idItem;

    public ItemImageDTO(
        Long id,
        String fileName,
        String filePath,
        Long idItem
      ) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.idItem = idItem;
    }

    // public Long getId() {
    //     return id;
    // }

    // public String getFileName() {
    //     return fileName;
    // }

    //    public String getFilePath() {
    //     return filePath;
    // }

    // public Long getIdItem() {
    //     return idItem;
    // }
}
