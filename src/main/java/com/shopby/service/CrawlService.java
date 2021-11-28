package com.shopby.service;

import com.shopby.model.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrawlService {
    private final static String SHOPPING_MALL_URLS = "https://www.gsshop.com/shop/sect/sectM.gs?sectid=1087057&eh=eyJjYXRlZ29yeSI6IjQyMTIyMF8xMDg3MDU3XzE0MzI4NDIifQ%3D%3D";

    public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36";

    public List<ItemDto> shoppingItemDtoList() throws IOException {
        List<ItemDto> itemDtoList = new ArrayList<>();

        Document document = Jsoup.connect(SHOPPING_MALL_URLS)
                .userAgent(USER_AGENT)
                .get();
        System.out.println(document);

        Elements elements = document.select("section[class=prd-list] ul li");

        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            String itemUrl = element.select("a[class=prd-item]").attr("href");
            String imageUrl = "https:" + element.select("div[class=prd-img] > img").attr("src");
            String name = element.select("dl[class=prd-info] > dt[class=prd-name]").text();
            String price = element.select("dl[class=prd-info] > span[class=set-price]").text();

            ItemDto itemDto = new ItemDto(i + 1, itemUrl, imageUrl, name, price, "");
            itemDtoList.add(itemDto);
            System.out.println(itemDto);
        }

        return itemDtoList;
    }
}
