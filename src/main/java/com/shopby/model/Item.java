package com.shopby.model;

import com.shopby.utils.StringSplitBuilder;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PayHistory> payHistories = new ArrayList<>();

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
        return StringSplitBuilder.getSplitPrice(price);
    }

    public List<Item> getItems(Delivery delivery) {
        return payHistories.stream()
                .filter(p-> p.getDelivery().equals(delivery))
                .map(PayHistory::getItem)
                .collect(Collectors.toList());
    }

    public List<Delivery> getCart(Item item, LocalDateTime time) {
        return payHistories.stream()
                .filter(p -> p.getItem().equals(item))
                .map(PayHistory::getDelivery)
                .filter(d -> d.getCreatedData().equals(time))
                .collect(Collectors.toList());
    }
}
