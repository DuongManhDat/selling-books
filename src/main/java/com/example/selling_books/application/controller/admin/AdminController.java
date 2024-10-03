package com.example.selling_books.application.controller.admin;

import com.example.selling_books.application.entity.Book;
import com.example.selling_books.application.model.dto.BookDTO;
import com.example.selling_books.application.model.dto.CategoryDTO;
import com.example.selling_books.application.model.dto.UserDTO;
import com.example.selling_books.application.model.request.CreateBookRequest;
import com.example.selling_books.application.model.request.CreateCategoryRequest;
import com.example.selling_books.application.service.BookService;
import com.example.selling_books.application.service.CategoryService;
import com.example.selling_books.application.service.CloudinaryService;
import com.example.selling_books.application.service.UserService;
import com.example.selling_books.application.utils.CoverType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/admin")
public class AdminController {

    private final UserService userService;

    private final CategoryService categoryService;

    private final BookService bookService;

    private final CloudinaryService cloudinaryService;

    @Value("${api.prefix}")
    private String apiPrefix;

    //Home
    @GetMapping("/home")
    public String getHome(Model theModel) {
        theModel.addAttribute("apiPrefix",apiPrefix);
        return "admin/home";
    }
    //User
    @GetMapping("/users")
    public String getListUser(Model theModel,
                              @RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int limit) {
        PageRequest pageRequest = PageRequest.of(page-1, limit, Sort.by("id").ascending());
        Page<UserDTO> userPages = userService.getListUser(pageRequest);
        int totalPages = userPages.getTotalPages();
        List<UserDTO> users = userPages.getContent();
        theModel.addAttribute("users",users);
        theModel.addAttribute("totalPages",totalPages);
        theModel.addAttribute("apiPrefix", apiPrefix);
        return "admin/user";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable long id) {
        userService.deleteUserById(id);
        return "admin/user";
    }


    //Order
    @GetMapping("/orders")
    public String getListOrders(Model theModel) {
        theModel.addAttribute("apiPrefix", apiPrefix);
        return "admin/order";
    }

    //Category
    @GetMapping("/categories")
    public String getListCategories(Model theModel,
                                    @RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "10") int limit) {
        PageRequest pageRequest = PageRequest.of(page-1, limit, Sort.by("id").ascending());
        Page<CategoryDTO> categoryPage =categoryService.getListCategories(pageRequest);
        int totalPages = categoryPage.getTotalPages();
        List<CategoryDTO> categories = categoryPage.getContent();
        theModel.addAttribute("categories",categories);
        theModel.addAttribute("totalPages",totalPages);
        theModel.addAttribute("apiPrefix",apiPrefix);
        theModel.addAttribute("category", new CreateCategoryRequest());
        return "admin/category";
    }

    @PostMapping("/categories")
    public String createCategory(@ModelAttribute("category") CreateCategoryRequest categoryRequest) {
        categoryService.createCategory(categoryRequest);
        return "redirect:/api/v1/admin/categories";
    }

    @DeleteMapping("/categories/{categoryId}")
    public String deleteCategoryById(@PathVariable Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return "redirect:/api/v1/admin/categories";
    }

    // Book

    @GetMapping("/books")
    public String getListBooks(Model theModel,
                               @RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "10") int limit) {
        PageRequest pageRequest = PageRequest.of(page-1, limit, Sort.by("id").ascending());
        Page<BookDTO> bookPage = bookService.getListBooks(pageRequest);
        int totalPages = bookPage.getTotalPages();
        List<BookDTO> books = bookPage.getContent();
        List<String> coverTypes = CoverType.getDisplayNames();
        List<CategoryDTO> categories = categoryService.getListCategories();
        theModel.addAttribute("totalPages", totalPages);
        theModel.addAttribute("books",books);
        theModel.addAttribute("apiPrefix",apiPrefix);
        theModel.addAttribute("categories",categories);
        theModel.addAttribute("book", new CreateBookRequest());
        theModel.addAttribute("coverTypes", coverTypes);
        return "admin/book";
    }

    @GetMapping("/books/{bookId}")
    @ResponseBody
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long bookId) {
        BookDTO book = bookService.getBookById(bookId);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/books")
    public String createBook(@ModelAttribute("book") CreateBookRequest createBookRequest,
                             @RequestParam("image") MultipartFile image,
                             @RequestParam("images") List<MultipartFile> images) {
        if (image != null && !image.isEmpty()) {
            String thumbnailUrl = cloudinaryService.uploadImage(image);
            createBookRequest.setThumbnail(thumbnailUrl);
        }
        if (images != null && images.size() > 0) {
            List<String> imgUrls = cloudinaryService.uploadImage(images);
            createBookRequest.setBookImages(imgUrls);
        }
        bookService.createBook(createBookRequest);
        return "redirect:/api/v1/admin/books";
    }

    @PutMapping("/books/{bookId}")
    public String updateBookById(@PathVariable Long bookId,
                                 CreateBookRequest createBookRequest,
                                 @RequestParam("image") MultipartFile image,
                                 @RequestParam("images") List<MultipartFile> images) {
        if (image != null && !image.isEmpty()) {
            String thumbnailUrl = cloudinaryService.uploadImage(image);
            createBookRequest.setThumbnail(thumbnailUrl);
        }
        if (images != null && images.size() > 0) {
            List<String> imgUrls = cloudinaryService.uploadImage(images);
            createBookRequest.setBookImages(imgUrls);
        }
        bookService.updateBookById(bookId,createBookRequest);
        return "redirect:/api/v1/admin/books";
    }

    @DeleteMapping("/books/{bookId}")
    public String deleteBookById(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
        return "redirect:/api/v1/admin/books";
    }
}
