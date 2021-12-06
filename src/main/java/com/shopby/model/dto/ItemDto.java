package com.shopby.model.dto;

import com.shopby.model.Item;
import lombok.Data;

@Data
public class ItemDto {

    private Long id;
    private String itemUrl;
    private String thumbnail;
    private String name;
    private String brand;
    private String price;

    public ItemDto(Item entity) {
        this.id = entity.getId();
        this.thumbnail = entity.getThumbnailImage();
        this.name = entity.getName();
        this.brand = entity.getBrand();
        this.price = entity.getSplitPrice();
    }

}
