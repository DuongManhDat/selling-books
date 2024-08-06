package com.example.selling_books.application.service;

import com.example.selling_books.application.entity.Book;
import com.example.selling_books.application.model.dto.BookDTO;
import com.example.selling_books.application.model.request.CreateBookRequest;
import com.example.selling_books.application.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

    @Override
    public Page<BookDTO> getListBooks(PageRequest pageRequest) {
        Page<Book> books = bookRepository.queryBooks(pageRequest);
        return books.map(book -> modelMapper.map(book, BookDTO.class));
    }

    @Override
    public BookDTO createBook(CreateBookRequest createBookRequest) {
        return null;
    }

    @Override
    public BookDTO updateBookById(long id, CreateBookRequest createBookRequest) {
        return null;
    }

    @Override
    public void importBookById(long id) {

    }

    @Override
    public void deleteBookById(long id) {

    }
}
