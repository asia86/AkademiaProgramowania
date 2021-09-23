package com.example.onlineshoppostgres.controller;

import com.example.onlineshoppostgres.model.Cart;
import com.example.onlineshoppostgres.model.Category;
import com.example.onlineshoppostgres.model.Product;
import com.example.onlineshoppostgres.repository.CartRepository;
import com.example.onlineshoppostgres.repository.ProductRepository;
import com.example.onlineshoppostgres.service.CartService;
import com.example.onlineshoppostgres.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("")
public class ProductController {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CategoryService categoryService;
    private final CartService cartService;

    public ProductController(ProductRepository productRepository, CartRepository cartRepository, CategoryService categoryService, CartService cartService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.categoryService = categoryService;
        this.cartService = cartService;
    }



    @GetMapping("/")
    public String homepage(Model model){

        System.out.println("--------------------------------------test----------------");
        return "home";
    }
    @GetMapping("/products")
    public String getAllProduct(Model model){
        model.addAttribute("products", this.productRepository.findAll());
        model.addAttribute("categories", this.categoryService.getCategoryList());
        return "shop_products_list";
    }

    @GetMapping("/categories")
    public String getCategory(@RequestParam("name")String name, Model model){
        model.addAttribute("category",this.categoryService.getCategoryByName(name));
        model.addAttribute("products", this.productRepository.findAllByCategory(Category.valueOf(name)));
        return "category";
    }

    @RequestMapping("/add_to_cart")
    public String addProduct(@RequestParam("name") String name, @ModelAttribute Cart cart){
       cartService.addProductToCart(productRepository.findByName(name), 1);

        return "redirect:/cart";
    }

    @RequestMapping("/cart")
    public String getCart(Model model){
        Map<Product,Integer> productList = new HashMap<>();

        double total = 0.0;
        List<Cart> cartList = cartRepository.findAll();
        for(Cart item: cartList){
            productList.put(item.getProduct(), item.getAmount());
            total+=item.getAmount()*item.getProduct().getPrice();
        }
        model.addAttribute("products", productList);
        model.addAttribute("total", total);

        return "cart";
    }

    @RequestMapping("/delete")
    public String deleteItemFromCart(@RequestParam ("id") Long id){

        cartRepository.delete(cartRepository.findByProduct_ProductId(id));

        return "redirect:/cart";
    }




}
