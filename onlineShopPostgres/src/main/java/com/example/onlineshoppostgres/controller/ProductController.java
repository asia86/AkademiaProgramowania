package com.example.onlineshoppostgres.controller;

import com.example.onlineshoppostgres.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ProductController {

    private final ProductRepository productRepository;


    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String homepage(Model model){
        return "home";
    }
    @GetMapping("/products")
    public String getAllProduct(Model model){
        model.addAttribute("products", this.productRepository.findAll());
        return "shop_products_list";
    }


}
