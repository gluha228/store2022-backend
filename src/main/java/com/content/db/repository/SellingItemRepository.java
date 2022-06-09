package com.content.db.repository;

import com.content.db.entity.SellingItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellingItemRepository extends JpaRepository<SellingItem, Long> {
}