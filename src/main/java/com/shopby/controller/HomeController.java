package com.shopby.controller;

import com.shopby.model.dto.ItemDto;
import com.shopby.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class HomeController {

    private final ItemService itemService;

    @GetMapping
    public String home(ModelMap model) {
        List<ItemDto> itemDtoList = itemService.findAll();
        model.addAttribute("itemList", itemDtoList);
        return "home";
    }
}
