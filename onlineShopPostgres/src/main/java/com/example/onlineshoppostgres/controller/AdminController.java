package com.example.onlineshoppostgres.controller;

import com.example.onlineshoppostgres.model.Category;
import com.example.onlineshoppostgres.model.Product;
import com.example.onlineshoppostgres.repository.ProductRepository;
import com.example.onlineshoppostgres.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("admin")
public class AdminController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    public AdminController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }


    @GetMapping("/products")
    public String listitems(Model model) {

        model.addAttribute("Items", productRepository.findAll());
        model.addAttribute("categories", Category.values());
        return "admin_product_list";
    }

    @GetMapping("/products-form")
    public String add_item(Model model) {

        model.addAttribute("Item", new Product());
        model.addAttribute("categories", Category.values());
        return "item_form";
    }

    @PostMapping("/products")
    public String saveItem(@ModelAttribute("Item")Product item){

        productService.addItem(item);
        return "redirect:/admin/products";
    }

    @PutMapping("/products/{id}")
    public String upd_item(@PathVariable("id")Long id, Model model ){
        System.out.println("uptade test");
        Product item=productRepository.findByProductId(id);
        model.addAttribute("Item",item);
        model.addAttribute("categories", Category.values());

        return "update_item";

    }


    @DeleteMapping("/products/{id}")
    public String del_item(@PathVariable("id")Long id){

        productRepository.delete(productRepository.findByProductId(id));
        return "redirect:/admin/products";
    }
}
