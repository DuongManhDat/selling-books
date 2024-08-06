package com.example.selling_books.application.service;

import com.example.selling_books.application.model.dto.BookDTO;
import com.example.selling_books.application.model.request.CreateBookRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface BookService {

    Page<BookDTO> getListBooks (PageRequest pageRequest);

    BookDTO createBook (CreateBookRequest createBookRequest);

    BookDTO updateBookById(long id, CreateBookRequest createBookRequest);

    void importBookById(long id);

    void deleteBookById(long id);
}
