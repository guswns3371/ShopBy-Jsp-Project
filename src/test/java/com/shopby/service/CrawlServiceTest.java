package com.shopby.service;

import com.shopby.model.Item;
import com.shopby.model.dto.ItemDto;
import com.shopby.model.dto.ItemInfoDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class CrawlServiceTest {

    @Autowired
    private CrawlService crawlService;

    public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36";

    @Test
    public void test() throws Exception {
        List<ItemDto> itemDtoList = new ArrayList<>();
        String url = "https://www.wconcept.co.kr/Men/0010";
        Document document = Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .get();

        Elements elements = document.select("div[class=thumbnail_list ] > ul > li");
        for (Element element : elements) {
            String itemUrl = "https://www.wconcept.co.kr" + element.select("a").attr("href");
            String imageUrl = "https:" + element.select("div[class=img] > img").attr("src");
            Element info = element.selectFirst("div[class=text max]");
            String brand = info.select("div[class=text_wrap] > div[class=brand]").text();
            String name = info.select("div[class=text_wrap] > div[class=product ellipsis multiline]").text();
            int price = Integer.parseInt(info.select("div[class=price] > span[class=discount_price]").text()
                    .replaceAll(",", ""));

            ItemDto itemDto = ItemDto.builder()
                    .itemUrl(itemUrl)
                    .imageUrl(imageUrl)
                    .brand(brand)
                    .name(name)
                    .price(price)
                    .build();

            System.out.println(itemDto);
            itemDtoList.add(itemDto);
        }
    }

    @Test
    public void itemTest() throws Exception {
        String itemUrl = "https://www.wconcept.co.kr/Product/301545918";
        Document document = Jsoup.connect(itemUrl)
                .userAgent(USER_AGENT)
                .get();

        Element headElement = document.selectFirst("div[class=pdt_detail] > div[class=pdt_head]");
        Element detailElement = document.selectFirst("div[class=pdt_contents detail] > div[class=marketing]");

        String itemImageUrl = "https:" + headElement.selectFirst("div[class=left_col] > div[class=img_goods] > div[class=img_area] > img").attr("src");
        String brand = headElement.selectFirst("div[class=right_col pdt_info] > form > div[class=h_group] > h2[class=brand] > a").text();
        int price = Integer.parseInt(headElement.selectFirst("div[class=right_col pdt_info] > form > div[class=price_wrap] > dl > dd[class=sale] > em").text()
                .replaceAll(",", ""));

        String information = detailElement.select("div").html();
//        ArrayList<String> infoImages = new ArrayList<>();
//        for (Element infoImage : detailElement.select("div > img")) {
//            infoImages.add(infoImage.attr("src"));
//        }

        ItemInfoDto build = ItemInfoDto.builder()
                .itemImageUrl(itemImageUrl)
                .brand(brand)
                .price(price)
                .information(information)
                .build();

    }
}