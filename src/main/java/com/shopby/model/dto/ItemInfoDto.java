package com.shopby.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ItemInfoDto {
    private String itemImageUrl;
    private String brand;
    private String name;
    private int price;
    private String information;

    @Builder
    public ItemInfoDto(String itemImageUrl, String brand, String name, int price, String information) {
        this.itemImageUrl = itemImageUrl;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.information = information;
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
