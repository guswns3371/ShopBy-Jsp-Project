package com.shopby.service;

import com.shopby.model.Item;
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
    public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36";

    public List<Item> wConceptMen() throws IOException, InterruptedException {
        List<Item> items = new ArrayList<>();

        String url = "https://www.wconcept.co.kr/Men/0010?size=60&page=";
        int count = 0;
        for (int i = 3; i < 5; i++) {
            Document document = Jsoup.connect(url + i)
                    .userAgent(USER_AGENT)
                    .get();

            Elements elements = document.select("div[class=thumbnail_list ] > ul > li");
            for (Element element : elements) {
                String itemUrl = element.select("a").attr("href");
                String thumbnailImage = "https:" + element.select("div[class=img] > img").attr("src");

                document = Jsoup.connect("https://www.wconcept.co.kr" + itemUrl)
                        .userAgent(USER_AGENT)
                        .get();

                Element headElement = document.selectFirst("div[class=pdt_detail] > div[class=pdt_head]");
                Element detailElement = document.selectFirst("div[class=pdt_contents detail] > div[class=marketing]");

                String image = "https:" + headElement.selectFirst("div[class=left_col] > div[class=img_goods] > div[class=img_area] > img").attr("src");
                String brand = headElement.selectFirst("div[class=right_col pdt_info] > form > div[class=h_group] > h2[class=brand] > a").text();
                String name = headElement.selectFirst("div[class=right_col pdt_info] > form > div[class=h_group] > h3").text();
                int price = Integer.parseInt(headElement.selectFirst("div[class=right_col pdt_info] > form > div[class=price_wrap] > dl > dd[class=sale] > em").text()
                        .replaceAll(",", ""));

                String information = detailElement.select("div").html();
                Item item = Item.builder()
                        .thumbnailImage(thumbnailImage)
                        .image(image)
                        .brand(brand)
                        .name(name)
                        .price(price)
                        .information(information)
                        .build();
                items.add(item);

                System.out.println((count++) + ": " + item);
                Thread.sleep(1500);
            }
        }

        return items;
    }

    /**
     public List<ItemDto> wConcept() throws IOException {
     List<ItemDto> itemDtoList = new ArrayList<>();
     String url = "https://www.wconcept.co.kr/Men/0010?page=";
     for (int i = 1; i <= 2; i++) {
     Document document = Jsoup.connect(url + i)
     .userAgent(USER_AGENT)
     .get();

     Elements elements = document.select("div[class=thumbnail_list ] > ul > li");
     for (Element element : elements) {
     String itemUrl = element.select("a").attr("href");
     String imageUrl = "https:" + element.select("div[class=img] > img").attr("src");
     Element info = element.selectFirst("div[class=text max]");
     String brand = info.select("div[class=text_wrap] > div[class=brand]").text();
     String name = info.select("div[class=text_wrap] > div[class=product ellipsis multiline]").text();
     int price = Integer.parseInt(info.select("div[class=price] > span[class=discount_price]").text()
     .replaceAll(",", ""));

     ItemDto itemDto = ItemDto.builder()
     .itemUrl(itemUrl)
     .thumbnail(imageUrl)
     .brand(brand)
     .name(name)
     .price(price)
     .build();

     itemDtoList.add(itemDto);
     }
     }
     return itemDtoList;
     }

     public ItemInfoDto wConceptItem(String itemUrl) throws IOException {
     Document document = Jsoup.connect("https://www.wconcept.co.kr" + itemUrl)
     .userAgent(USER_AGENT)
     .get();

     Element headElement = document.selectFirst("div[class=pdt_detail] > div[class=pdt_head]");
     Element detailElement = document.selectFirst("div[class=pdt_contents detail] > div[class=marketing]");

     String itemImageUrl = "https:" + headElement.selectFirst("div[class=left_col] > div[class=img_goods] > div[class=img_area] > img").attr("src");
     String brand = headElement.selectFirst("div[class=right_col pdt_info] > form > div[class=h_group] > h2[class=brand] > a").text();
     String name = headElement.selectFirst("div[class=right_col pdt_info] > form > div[class=h_group] > h3").text();
     int price = Integer.parseInt(headElement.selectFirst("div[class=right_col pdt_info] > form > div[class=price_wrap] > dl > dd[class=sale] > em").text()
     .replaceAll(",", ""));

     String information = detailElement.select("div").html();

     return ItemInfoDto.builder()
     .image(itemImageUrl)
     .brand(brand)
     .name(name)
     .price(price)
     .information(information)
     .build();
     }

     public List<ItemDto> gsShop() throws IOException {
     List<ItemDto> itemDtoList = new ArrayList<>();
     String url = "https://www.gsshop.com/shop/sect/sectM.gs?sectid=1087057&eh=eyJjYXRlZ29yeSI6IjQyMTIyMF8xMDg3MDU3XzE0MzI4NDIifQ%3D%3D";
     Document document = Jsoup.connect(url)
     .userAgent(USER_AGENT)
     .get();

     Elements elements = document.select("section[class=prd-list] ul li");

     for (Element element : elements) {
     String itemUrl = element.select("a[class=prd-item]").attr("href");
     String imageUrl = "https:" + element.select("div[class=prd-img] > img").attr("src");
     String name = element.select("dl[class=prd-info] > dt[class=prd-name]").text();
     String price = element.select("dl[class=prd-info] > dd[class=price-info] > span[class=price] > span[class=set-price] > strong").text() + "원";

     ItemDto itemDto = ItemDto.builder()
     .itemUrl(itemUrl)
     .thumbnail(imageUrl)
     .name(name)
     .price(0)
     .build();
     itemDtoList.add(itemDto);
     }

     return itemDtoList;
     }
     **/
}
