package com.shopby.controller;

import com.shopby.model.dto.ItemDto;
import com.shopby.service.CrawlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class HomeController {

    private final CrawlService crawlService;

    @GetMapping
    public String home(ModelMap model) throws IOException {
        List<ItemDto> itemDtoList = crawlService.shoppingItemDtoList();
        model.addAttribute("itemList", itemDtoList);
        return "home";
    }
}
