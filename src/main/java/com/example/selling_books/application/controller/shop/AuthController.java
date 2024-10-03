package com.example.selling_books.application.controller.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/customer/account")
public class AuthController {

    @Value("${api.prefix}")
    private String apiPrefix;

    @RequestMapping("/login")
    public String login(Model theModel) {
        theModel.addAttribute("apiPrefix", apiPrefix);
        return "shop/login";
    }
}
