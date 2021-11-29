package com.shopby.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class ItemDto {

    private int id;
    private String itemUrl;
    private String imageUrl;
    private String name;
    private String brand;
    private int price;

    @Builder
    public ItemDto(String itemUrl, String imageUrl, String name, String brand, int price) {
        this.itemUrl = itemUrl;
        this.imageUrl = imageUrl;
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public String getSplitPrice() {
        StringBuilder builder = new StringBuilder();
        String strPrice = Integer.toString(price);
        int i = strPrice.length() % 3;
        builder.append(strPrice, 0, i);

        while (i < strPrice.length()) {
            if (!builder.toString().equals("")) {
                builder.append(",");
            }
            builder.append(strPrice, i, i + 3);
            i += 3;
        }
        return builder.toString();
    }
}
