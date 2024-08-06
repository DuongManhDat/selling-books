package com.example.selling_books.application.controller;

import com.example.selling_books.application.model.dto.CategoryDTO;
import com.example.selling_books.application.model.dto.UserDTO;
import com.example.selling_books.application.model.request.CreateCategoryRequest;
import com.example.selling_books.application.service.CategoryService;
import com.example.selling_books.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/admin")
public class AdminController {

    private final UserService userService;

    private final CategoryService categoryService;

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

    //Book
    @GetMapping("/books")
    public String getListBooks(Model theModel) {
        theModel.addAttribute("apiPrefix",apiPrefix);
        return "admin/book";
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

}
