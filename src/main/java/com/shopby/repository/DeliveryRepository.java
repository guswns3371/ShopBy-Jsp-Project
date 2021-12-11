package com.shopby.repository;

import com.shopby.model.Delivery;
import com.shopby.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findDeliveriesByUserOrderByCreatedDataDesc(User user);
}
