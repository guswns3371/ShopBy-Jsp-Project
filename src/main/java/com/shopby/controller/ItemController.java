package com.shopby.controller;

import com.shopby.model.dto.ItemInfoDto;
import com.shopby.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController extends HttpServlet {

    private final ItemService itemService;

    @GetMapping("/{id}")
    public String item(@PathVariable("id") Long itemId, ModelMap model) throws IOException {
        ItemInfoDto itemInfoDto = itemService.findById(itemId);
        model.addAttribute("itemInfo", itemInfoDto);
        return "item";
    }
}
