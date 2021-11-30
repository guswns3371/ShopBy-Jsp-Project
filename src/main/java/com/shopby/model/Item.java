package com.shopby.model;

import com.shopby.model.dto.ItemDto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    private String image;
    private String thumbnailImage;

    @Column(columnDefinition = "LONGTEXT")
    private String information;

    @Builder
    public Item(String brand, String name, int price, String image, String thumbnailImage, String information) {
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.image = image;
        this.thumbnailImage = thumbnailImage;
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
