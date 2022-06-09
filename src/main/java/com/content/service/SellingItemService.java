package com.content.service;

import com.content.db.entity.SellingItem;
import com.content.db.repository.SellingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SellingItemService {
    SellingItemRepository sellingItemRepository;

    @Autowired
    public SellingItemService(SellingItemRepository sellingItemRepository) {
        this.sellingItemRepository = sellingItemRepository;
        assignItems();
    }
    public void assignItems() {
        List<SellingItem> items = new ArrayList<>();
        items.add(new SellingItem("Vostok precision class", "Urss", 1965, "Wristwatch", "Wostok 2809", "Wostok", "Swiss", "Manual","0"));
        items.add(new SellingItem("Volna", "Urss", 1960, "Wristwatch", "Wostok 2809A", "ChChZ", "Swiss", "Manual","1"));
        items.add(new SellingItem("Roamer Jubile", "Swiss", 1958, "Wristwatch", "MST 402", "Roamer", "Swiss", "Manual","2"));
        items.add(new SellingItem("Cylindre 4 rubis", "France", 1890, "Pocket watch", "38mm", "Remontoir","Cylinder", "Key","3"));
        items.add(new SellingItem("Vostok precision class", "Urss", 1962, "Wristwatch", "Wostok 2809A", "Wostok", "Swiss", "Manual","4"));
        items.add(new SellingItem("Volna", "Urss", 1960, "Wristwatch", "Wostok 2809A", "ChChZ", "Swiss", "Manual","1"));
        items.add(new SellingItem("Volna", "Urss", 1960, "Wristwatch", "Wostok 2809A", "ChChZ", "Swiss", "Manual","1"));
        items.add(new SellingItem("Volna", "Urss", 1960, "Wristwatch", "Wostok 2809A", "ChChZ", "Swiss", "Manual","1"));
        items.add(new SellingItem("Volna", "Urss", 1960, "Wristwatch", "Wostok 2809A", "ChChZ", "Swiss", "Manual","1"));
        items.add(new SellingItem("Volna", "Urss", 1960, "Wristwatch", "Wostok 2809A", "ChChZ", "Swiss", "Manual","1"));
        items.add(new SellingItem("Volna", "Urss", 1960, "Wristwatch", "Wostok 2809A", "ChChZ", "Swiss", "Manual","1"));
        items.add(new SellingItem("Volna", "Urss", 1960, "Wristwatch", "Wostok 2809A", "ChChZ", "Swiss", "Manual","1"));
        items.add(new SellingItem("Vostok precision class", "Urss", 1965, "Wristwatch", "Wostok 2809", "Wostok", "Swiss", "Manual","0"));
        items.add(new SellingItem("Volna", "Urss", 1960, "Wristwatch", "Wostok 2809A", "ChChZ", "Swiss", "Manual","1"));
        items.add(new SellingItem("Roamer Jubile", "Swiss", 1958, "Wristwatch", "MST 402", "Roamer", "Swiss", "Manual","2"));
        items.add(new SellingItem("Cylindre 4 rubis", "France", 1890, "Pocket watch", "38mm", "Remontoir","Cylinder", "Key","3"));
        items.add(new SellingItem("Vostok precision class", "Urss", 1962, "Wristwatch", "Wostok 2809A", "Wostok", "Swiss", "Manual","4"));
        items.add(new SellingItem("Vostok precision class", "Urss", 1965, "Wristwatch", "Wostok 2809", "Wostok", "Swiss", "Manual","0"));
        items.add(new SellingItem("Volna", "Urss", 1960, "Wristwatch", "Wostok 2809A", "ChChZ", "Swiss", "Manual","1"));
        items.add(new SellingItem("Roamer Jubile", "Swiss", 1958, "Wristwatch", "MST 402", "Roamer", "Swiss", "Manual","2"));
        items.add(new SellingItem("Cylindre 4 rubis", "France", 1890, "Pocket watch", "38mm", "Remontoir","Cylinder", "Key","3"));
        items.add(new SellingItem("Vostok precision class", "Urss", 1962, "Wristwatch", "Wostok 2809A", "Wostok", "Swiss", "Manual","4"));
        items.add(new SellingItem("Volna", "Urss", 1960, "Wristwatch", "Wostok 2809A", "ChChZ", "Swiss", "Manual","1"));
        items.add(new SellingItem("Volna", "Urss", 1960, "Wristwatch", "Wostok 2809A", "ChChZ", "Swiss", "Manual","1"));
        items.add(new SellingItem("Volna", "Urss", 1960, "Wristwatch", "Wostok 2809A", "ChChZ", "Swiss", "Manual","1"));
        sellingItemRepository.saveAll(items);
    }

    public List<SellingItem> getAllItems() {
        return sellingItemRepository.findAll();
    }
}
