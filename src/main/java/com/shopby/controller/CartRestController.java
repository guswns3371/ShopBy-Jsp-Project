package com.shopby.controller;

import com.shopby.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CartRestController {

    private final DeliveryService deliveryService;

    @RequestMapping(value = "/cart/checkout", method = RequestMethod.POST)
    public boolean checkout(@RequestBody Map<String, Object> map,
                            HttpSession session,
                            HttpServletRequest request) {
        log.error(map.toString());

        String userId = (String) map.get("userId");
        List<Integer> itemCounts = (List<Integer>) map.get("itemCounts");
        List<Integer> itemIds = (List<Integer>) map.get("itemIds");
        List<Long> longItemIds = itemIds.stream().map(Integer::longValue).collect(Collectors.toList());

        deliveryService.checkout(userId, longItemIds, itemCounts);

        HashMap<Long, Integer> cart = (HashMap<Long, Integer>) session.getAttribute("cart" + userId);
        for (Long longItemId : longItemIds) {
            cart.remove(longItemId);
        }
        return true;
    }


}
