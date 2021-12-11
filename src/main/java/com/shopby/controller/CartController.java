package com.shopby.controller;

import com.shopby.model.dto.CartItemDto;
import com.shopby.model.dto.DeliveryDto;
import com.shopby.service.DeliveryService;
import com.shopby.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final ItemService itemService;
    private final DeliveryService deliveryService;

    @GetMapping("/{id}")
    public String cart(@PathVariable("id") String userId,
                       HttpSession session,
                       ModelMap model) {
        HashMap<Long, Integer> cart = (HashMap<Long, Integer>) session.getAttribute("cart" + userId);
        List<CartItemDto> cartItems = itemService.findCartItemsByIdList(cart);
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

    @GetMapping("/delivery/{id}")
    public String deliveryHistory(@PathVariable("id") String userId,
                                  ModelMap model) {

        List<DeliveryDto> deliveryHistory = deliveryService.getDeliveryHistory(userId);
        model.addAttribute("deliveryHistory", deliveryHistory);
        return "deliveryHistory";
    }
}
