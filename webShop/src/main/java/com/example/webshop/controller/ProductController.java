package com.example.webshop.controller;

import com.example.webshop.repository.ProductDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/products")
    public String getProduckts(Model model){
        model.addAttribute("products", productDao.all());
        return "list";
    }

    @GetMapping("/products/{name}")
    public String getProduct(@PathVariable String name, Model model){
        model.addAttribute("product", productDao.byName(name));
        return "product";
    }
}
