package com.example.selling_books.application.service;

import com.example.selling_books.application.entity.Book;
import com.example.selling_books.application.entity.BookImage;
import com.example.selling_books.application.entity.Category;
import com.example.selling_books.application.exception.BadRequestException;
import com.example.selling_books.application.model.dto.BookDTO;
import com.example.selling_books.application.model.request.CreateBookRequest;
import com.example.selling_books.application.repository.BookImageRepository;
import com.example.selling_books.application.repository.BookRepository;
import com.example.selling_books.application.repository.CategoryRepository;
import com.example.selling_books.application.utils.CoverType;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    private final BookImageRepository bookImageRepository;

    private final ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;

    @Override
    public Page<BookDTO> getListBooks(PageRequest pageRequest) {
        Page<Book> books = bookRepository.queryBooks(pageRequest);
        return books.map(book -> modelMapper.map(book, BookDTO.class));
    }

    @Override
    public BookDTO getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if(!book.isPresent()) {
            throw new BadRequestException("Id này không tồn tài!");
        }
        return modelMapper.map(book.get(), BookDTO.class);
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
            .discount(createBookRequest.getDiscount())
            .stock(0)
            .categories(categories)
            .supplier(createBookRequest.getSupplier())
            .coverType(CoverType.fromDisplayName(createBookRequest.getCoverType()))
            .pageCount(createBookRequest.getPageCount())
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
        Optional<Book> existingBook = bookRepository.findById(id);
        if(!existingBook.isPresent()) {
            throw new BadRequestException("Không tìm thấy sách");
        }
        Book newBook = existingBook.get();
        Set<Category> categories = Optional.ofNullable(createBookRequest.getCategories())
            .orElse(Collections.emptyList())
            .stream()
            .map(category -> categoryRepository.findByName(category).orElse(null))
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());
        newBook.setName(createBookRequest.getName());
        newBook.setAuthor(createBookRequest.getAuthor());
        newBook.setPublisher(createBookRequest.getPublisher());
        newBook.setCategories(categories);
        newBook.setPublicationYear(createBookRequest.getPublicationYear());
        newBook.setDescription(createBookRequest.getDescription());
        newBook.setSellingPrice(createBookRequest.getSellingPrice());
        newBook.setCost(createBookRequest.getCost());
        newBook.setDiscount(createBookRequest.getDiscount());
        newBook.setSupplier(createBookRequest.getSupplier());
        newBook.setCoverType(CoverType.fromDisplayName(createBookRequest.getCoverType()));
        newBook.setPageCount(createBookRequest.getPageCount());
        Set<BookImage> bookImages = Optional.ofNullable(createBookRequest.getBookImages())
            .orElse(Collections.emptyList())
            .stream()
            .map(image -> BookImage.builder()
                .book(newBook)
                .imageUrl(image)
                .build())
            .collect(Collectors.toSet());
        if(createBookRequest.getThumbnail() != null) {
            newBook.setThumbnail(createBookRequest.getThumbnail());
        }
        if(bookImages.size() != 0) {
            bookImageRepository.deleteByBookId(id);
            newBook.setBookImages(bookImages);
        }
        newBook.setId(id);
        bookRepository.save(newBook);
        return modelMapper.map(newBook, BookDTO.class);
    }


    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
