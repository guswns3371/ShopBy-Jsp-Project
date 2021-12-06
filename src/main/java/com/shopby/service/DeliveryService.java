package com.shopby.service;

import com.shopby.model.Delivery;
import com.shopby.model.Item;
import com.shopby.model.PayHistory;
import com.shopby.model.User;
import com.shopby.repository.DeliveryRepository;
import com.shopby.repository.PayHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final PayHistoryRepository payHistoryRepository;
    private final UserService userService;
    private final ItemService itemService;

    @Transactional
    public void checkout(String userId, List<Long> itemIds, List<Integer> itemCounts) {
        Optional<User> optionalUser = userService.findByUserId(userId);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 유저입니다 = " + userId);
        }

        User user = optionalUser.get();
        List<Item> items = itemService.findItemsByIdList(itemIds);

        int totalPrice = getTotalPrice(itemCounts, items);
        Delivery delivery = deliveryRepository.save(new Delivery(user, totalPrice));

        List<PayHistory> payHistories = getPayHistories(itemCounts, items, delivery);
        payHistoryRepository.saveAll(payHistories);
    }


    private List<PayHistory> getPayHistories(List<Integer> itemCounts, List<Item> items, Delivery delivery) {
        List<PayHistory> payHistories = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            payHistories.add(new PayHistory(delivery, items.get(i), itemCounts.get(i)));
        }
        return payHistories;
    }

    private int getTotalPrice(List<Integer> itemCounts, List<Item> items) {
        int totalPrice = 0;
        for (int i = 0; i < items.size(); i++) {
            totalPrice += items.get(i).getPrice() * itemCounts.get(i);
        }
        return totalPrice;
    }
}
