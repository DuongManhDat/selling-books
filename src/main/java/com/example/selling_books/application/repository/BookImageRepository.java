package com.example.selling_books.application.repository;

import com.example.selling_books.application.entity.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookImageRepository extends JpaRepository<BookImage, Long> {
}
