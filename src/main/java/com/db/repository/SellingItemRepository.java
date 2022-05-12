package com.db.repository;

import com.db.entity.SellingItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellingItemRepository extends JpaRepository<SellingItem, Long> {
}