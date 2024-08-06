package com.example.selling_books.application.repository;

import com.example.selling_books.application.entity.OderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OderItem, Long> {
}
