package com.example.selling_books.application.controller;

import com.example.selling_books.application.entity.User;
import com.example.selling_books.application.model.dto.UserDTO;
import com.example.selling_books.application.model.request.CreateUserRequest;
import com.example.selling_books.application.repository.UserRepository;
import com.example.selling_books.application.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("${api.prefix}")
public class UserController {

    private final UserService userService;

    @Value("${api.prefix}")
    private String apiPrefix;

    @GetMapping("/signup")
    public String signUp(Model theModel) {
        theModel.addAttribute("apiPrefix",apiPrefix);
        theModel.addAttribute("user", new CreateUserRequest());
        return "shop/signup"; // Trả về tên của template, ví dụ: "hello" ứng với "hello.html"
    }

    @GetMapping("/signin")
    public String signIn(Model theModel) {
        theModel.addAttribute("apiPrefix",apiPrefix);
        return "shop/signin";
    }

    @PostMapping("/success")
    public String register (@ModelAttribute("user") CreateUserRequest theUser) {
        userService.createUser(theUser);
        return "shop/success";
    }
}
