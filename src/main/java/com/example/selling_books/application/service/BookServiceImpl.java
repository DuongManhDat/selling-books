package com.example.selling_books.application.service;

import com.example.selling_books.application.entity.Book;
import com.example.selling_books.application.entity.BookImage;
import com.example.selling_books.application.entity.Category;
import com.example.selling_books.application.exception.BadRequestException;
import com.example.selling_books.application.model.dto.BookDTO;
import com.example.selling_books.application.model.request.CreateBookRequest;
import com.example.selling_books.application.repository.BookRepository;
import com.example.selling_books.application.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;

    @Override
    public Page<BookDTO> getListBooks(PageRequest pageRequest) {
        Page<Book> books = bookRepository.queryBooks(pageRequest);
        return books.map(book -> modelMapper.map(book, BookDTO.class));
    }

    @Override
    public BookDTO createBook(CreateBookRequest createBookRequest) {
        Optional<Book> book = bookRepository.findByName(createBookRequest.getName());
        if(book.isPresent()) {
            throw new BadRequestException("Tên sách đã tồn tại");
        }
        Set<Category> categories = createBookRequest.getCategories().stream().map(category -> categoryRepository.findByName(category).get()).collect(Collectors.toSet());
        Book newBook = Book.builder()
            .name(createBookRequest.getName())
            .author(createBookRequest.getAuthor())
            .publisher(createBookRequest.getPublisher())
            .publicationYear(createBookRequest.getPublicationYear())
            .description(createBookRequest.getDescription())
            .thumbnail(createBookRequest.getThumbnail())
            .sellingPrice(createBookRequest.getSellingPrice())
            .cost(createBookRequest.getCost())
            .stock(0)
            .categories(categories)
            .build();
        Set<BookImage> bookImages = createBookRequest.getBookImages().stream().map(image ->
            BookImage.builder()
            .book(newBook)
            .imageUrl(image)
                .build()).collect(Collectors.toSet());
        newBook.setBookImages(bookImages);
        bookRepository.save(newBook);
        return modelMapper.map(newBook,BookDTO.class);
    }

    @Override
    public BookDTO updateBookById(Long id, CreateBookRequest createBookRequest) {
        Book newBook = modelMapper.map(createBookRequest,Book.class);
        newBook.setId(id);
        bookRepository.save(newBook);
        return modelMapper.map(newBook, BookDTO.class);
    }


    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
