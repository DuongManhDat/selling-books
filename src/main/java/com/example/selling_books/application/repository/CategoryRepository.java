package com.example.selling_books.application.repository;

import com.example.selling_books.application.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);
    @Query("SELECT c FROM Category c")
    Page<Category> queryCategories(Pageable pageable);
}
