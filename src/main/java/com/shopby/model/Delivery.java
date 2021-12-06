package com.shopby.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Delivery extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus = DeliveryStatus.READY;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PayHistory> payHistories = new ArrayList<>();

    public Delivery(User user, int totalPrice) {
        this.user = user;
        this.totalPrice = totalPrice;
    }

    public void startDelivery() {
        deliveryStatus = DeliveryStatus.ONGOING;
    }

    public void completeDelivery() {
        deliveryStatus = DeliveryStatus.COMPLETE;
    }

    public List<Item> getItems() {
        return payHistories.stream()
                .map(PayHistory::getItem)
                .collect(Collectors.toList());
    }
}
