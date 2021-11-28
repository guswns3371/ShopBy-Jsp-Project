package com.shopby.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;

@Controller
@RequestMapping("/item")
public class ItemController extends HttpServlet {

    @GetMapping
    public String item() {
        return "item";
    }
}
