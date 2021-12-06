package com.shopby.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class PayHistory {

    @Id
    @GeneratedValue
    @Column(name = "shopping_bag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    public PayHistory(Delivery delivery, Item item, int count) {
        setDelivery(delivery);
        setItem(item);
        this.count = count;
    }

    private void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        if (!delivery.getPayHistories().contains(this)) {
            delivery.getPayHistories().add(this);
        }
    }

    private void setItem(Item item) {
        this.item = item;
        if (!item.getPayHistories().contains(this)) {
            item.getPayHistories().add(this);
        }
    }

    public void remove() {
        delivery.getPayHistories().remove(this);
        item.getPayHistories().remove(this);
    }
}
