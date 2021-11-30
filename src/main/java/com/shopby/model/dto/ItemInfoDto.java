package com.shopby.model.dto;

import com.shopby.model.Item;
import lombok.Data;

@Data
public class ItemInfoDto {
    private Long id;
    private String image;
    private String thumbnail;
    private String brand;
    private String name;
    private String price;
    private String information;

    public ItemInfoDto(Item entity) {
        this.id = entity.getId();
        this.image = entity.getImage();
        this.thumbnail = entity.getThumbnailImage();
        this.brand = entity.getBrand();
        this.name = entity.getName();
        this.price = entity.getSplitPrice();
        this.information = entity.getInformation();
    }

}
