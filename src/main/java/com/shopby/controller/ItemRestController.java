package com.shopby.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ItemRestController {

    @RequestMapping(value = "/item/cart/{id}", method = RequestMethod.POST)
    public boolean addToCart(@PathVariable("id") Long itemId,
                            @RequestBody Map<String, Object> map,
                            HttpSession session,
                            HttpServletRequest request) {
        log.error(map.toString());
        String userId = (String) map.get("userId");
        int itemCount = (int) map.get("itemCount");
        HashMap<Long, Integer> sessions = (HashMap<Long, Integer>) session.getAttribute("cart" + userId);

        if (sessions == null) {
            HashMap<Long, Integer> cart = new HashMap<>();
            cart.put(itemId, itemCount);
            session.setAttribute("cart" + userId, cart);
        } else {
            sessions.put(itemId, itemCount);
            session.setAttribute("cart" + userId, sessions);
        }

        return true;
    }

    @RequestMapping(value = "/item/cart/{id}", method = RequestMethod.DELETE)
    public boolean removeFromCart(@PathVariable("id") Long itemId,
                                 @RequestBody Map<String, Object> map,
                                 HttpSession session,
                                 HttpServletRequest request) {

        String userId = (String) map.get("userId");
        HashMap<Long, Integer> sessions = (HashMap<Long, Integer>) session.getAttribute("cart" + userId);
        if (sessions != null) {
            sessions.remove(itemId);
            session.setAttribute("cart" + userId, sessions);
        }

        return true;
    }
}
