package com.example.selling_books.application.repository;

import com.example.selling_books.application.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
