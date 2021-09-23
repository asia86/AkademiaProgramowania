package com.example.onlineshoppostgres.controller;

import com.example.onlineshoppostgres.model.Category;
import com.example.onlineshoppostgres.model.Product;
import com.example.onlineshoppostgres.repository.ProductRepository;
import com.example.onlineshoppostgres.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("")
public class AdminController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    public AdminController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }


    @GetMapping("/admin")
    public String listitems(Model model) {

        model.addAttribute("Items", productRepository.findAll());
        model.addAttribute("categories", Category.values());
        return "admin_product_list";
    }

    @GetMapping("/additems")
    public String add_item(Model model) {

        model.addAttribute("Item", new Product());
        model.addAttribute("categories", Category.values());
        return "item_form";
    }

    @PostMapping("/saveitem")
    public String saveItem(@ModelAttribute("Item")Product item){

        productService.addItem(item);
        return "redirect:admin";
    }

    @GetMapping("/itemupd")
    public String upd_item(@RequestParam("name")String name, Model model ){

        Product item=productRepository.findByName(name);
        model.addAttribute("Item",item);
        return "update_item";

    }

    @PostMapping("save_updateitem")
    public String update_item(@RequestParam("name")String name,@ModelAttribute("Item")Product item) {

        productService.updateItem(item, name);
        return "redirect:admin";
    }


    @GetMapping("/itemdel")
    public String del_item(@RequestParam("name")String name){

        productRepository.delete(productRepository.findByName(name));
        return "redirect:admin";
    }
}
