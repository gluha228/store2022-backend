package com.controllers;

import com.db.entity.SellingItem;
import com.service.SellingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class mainController {
    SellingItemService sellingItemService;
    @Autowired
    public mainController(SellingItemService sellingItemService) {
        this.sellingItemService = sellingItemService;
    }

    @GetMapping("/items")
    public String getRequest() {
        System.out.println("basic");
        return "items";
    }

    @GetMapping("/getitems")
    @ResponseBody
    public List<SellingItem> getItemsList() {
        System.out.println("request");
        return sellingItemService.getAllItems();
    }
}
