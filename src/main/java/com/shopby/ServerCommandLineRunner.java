package com.shopby;

import com.shopby.service.CrawlService;
import com.shopby.service.ItemService;
import com.shopby.utils.file.FilesStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@RequiredArgsConstructor
@Component
public class ServerCommandLineRunner implements CommandLineRunner {

    @Resource
    private final FilesStorageService storageService;

    private final ItemService itemService;
    private final CrawlService crawlService;

    @Override
    public void run(String... args) throws Exception {
        storageService.init();

//        List<Item> items = crawlService.wConceptMen();
//        itemService.saveAll(items);
    }
}
