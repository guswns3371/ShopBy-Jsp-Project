package com.shopby.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDto {

    private int id;
    private String itemUrl;
    private String imageUrl;
    private String name;
    private String price;
    private String introduction;

}
