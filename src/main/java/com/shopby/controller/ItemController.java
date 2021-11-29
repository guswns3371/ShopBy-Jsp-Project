package com.shopby.controller;

import com.shopby.model.dto.ItemInfoDto;
import com.shopby.service.CrawlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController extends HttpServlet {

    private final CrawlService crawlService;

    @GetMapping("/Product/{id}")
    public String item(@PathVariable("id") String itemId, ModelMap model) throws IOException {
        ItemInfoDto itemInfoDto = crawlService.wConceptItem("/Product/" + itemId);
        model.addAttribute("itemInfo", itemInfoDto);
        return "item";
    }

}
