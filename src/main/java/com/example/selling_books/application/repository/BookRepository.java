package com.example.selling_books.application.repository;

import com.example.selling_books.application.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b")
    Page<Book> queryBooks(Pageable pageable);

    Optional<Book> findByName(String name);
}
