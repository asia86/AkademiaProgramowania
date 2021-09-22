package com.example.onlineshoppostgres.controller;

import com.example.onlineshoppostgres.model.Category;
import com.example.onlineshoppostgres.repository.ProductRepository;
import com.example.onlineshoppostgres.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductController(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String homepage(Model model){
        return "home";
    }
    @GetMapping("/products")
    public String getAllProduct(Model model){
        model.addAttribute("products", this.productRepository.findAll());
        model.addAttribute("categories", this.categoryService.getCategoryList());
        return "shop_products_list";
    }


}
