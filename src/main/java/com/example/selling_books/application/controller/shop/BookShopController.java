package com.example.selling_books.application.controller.shop;

import com.example.selling_books.application.model.dto.BookDTO;
import com.example.selling_books.application.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/customer")
public class BookShopController {

    private final BookService bookService;

    @Value("${api.prefix}")
    private String apiPrefix;

    @GetMapping("/books")
    public String getListBooks(Model theModel,
                               @RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "4") int limit) {
        PageRequest pageRequest = PageRequest.of(page-1, limit, Sort.by("id").ascending());
        Page<BookDTO> bookPage = bookService.getListBooks(pageRequest);
        int totalPages = bookPage.getTotalPages();
        List<BookDTO> books = bookPage.getContent();
        theModel.addAttribute("books",books);
        theModel.addAttribute("totalPages",totalPages);
        theModel.addAttribute("apiPrefix",apiPrefix);
        return "shop/index";
    }

    @GetMapping("/books/{bookId}")
    public String getBookById(@PathVariable Long bookId,
                              Model theModel) {
        BookDTO book = bookService.getBookById(bookId);
        theModel.addAttribute("book", book);
        return "shop/book_detail";
    }
}
