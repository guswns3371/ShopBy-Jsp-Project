package com.shopby.model.dto;

import com.shopby.model.Item;
import com.shopby.utils.StringSplitBuilder;
import lombok.Data;

import java.util.HashMap;

@Data
public class CartItemDto {
    private Long id;
    private String image;
    private String name;
    private int count;
    private int price;

    public CartItemDto(Item entity, HashMap<Long, Integer> cart) {
        this.id = entity.getId();
        this.image = entity.getThumbnailImage();
        this.name = entity.getName();
        this.count = cart.get(entity.getId());
        this.price = entity.getPrice();
    }
}
