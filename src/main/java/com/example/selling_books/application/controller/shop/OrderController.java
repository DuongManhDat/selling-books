package com.example.selling_books.application.controller.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/customer")
public class OrderController {

    @Value("${api.prefix}")
    private String apiPrefix;

    @RequestMapping("/shopping-cart")
    public String getShoppingCart(Model theModel) {
        theModel.addAttribute("apiPrefix", apiPrefix);
        return "shop/shopping_cart";
    }
}
