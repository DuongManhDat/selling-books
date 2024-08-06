package com.example.selling_books.application.repository;

import com.example.selling_books.application.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
