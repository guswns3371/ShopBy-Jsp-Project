package com.shopby.model;

import com.shopby.model.dto.ItemDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    private String imagePath;

    public Item(ItemDto dto) {
        this.name = dto.getName();
        this.brand = dto.getBrand();
        this.price = dto.getPrice();
        this.imagePath = dto.getImageUrl();
    }
}
