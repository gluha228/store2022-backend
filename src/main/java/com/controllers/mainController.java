package com.controllers;

import com.db.entity.SellingItem;
import com.service.SellingItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class mainController {
    private final Logger logger = LoggerFactory.getLogger(mainController.class);
    private final SellingItemService sellingItemService;
    @Autowired
    public mainController(SellingItemService sellingItemService) {
        this.sellingItemService = sellingItemService;
    }

    @GetMapping("/items")
    public String getRequest() {
        logger.info("basic");
        return "items";
    }

    @GetMapping("/getitems")
    @ResponseBody
    public List<SellingItem> getItemsList() {
        logger.info("request");
        return sellingItemService.getAllItems();
    }
}
