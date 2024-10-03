package com.example.selling_books.application.repository;

import com.example.selling_books.application.entity.BookImage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookImageRepository extends JpaRepository<BookImage, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM BookImage bi WHERE bi.book.id = ?1")
    void deleteByBookId(Long id);
}
