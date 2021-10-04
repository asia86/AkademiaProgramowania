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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;


@Controller
@RequestMapping("")
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final CartService cartService;

    public ProductController(ProductRepository productRepository, CategoryService categoryService, CartService cartService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.cartService = cartService;
    }



    @GetMapping("/")
    public String homepage(Model model){

        return "home";
    }
    @GetMapping("/products")
    public String getAllProduct(Model model){
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("categories", Category.values());
        return "shop_products_list";
    }

    @GetMapping("/categories")
    public String getCategory(@RequestParam("name")String name, Model model){
        model.addAttribute("category", categoryService.getCategoryByName(name));
        model.addAttribute("products", productRepository.findAllByCategory(Category.valueOf(name)));
        return "category";
    }


    @PostMapping(value = "/products")
    public String addProduct(@RequestParam("name") String name, @ModelAttribute Cart cart){
        System.out.println("test");
       cartService.addProductToCart(productRepository.findByName(name), 1);

        return "redirect:/cart-items";
    }

    @GetMapping("/cart-items")
    public String getCart(Model model){

        Map<Product,Integer> productList = cartService.getAllProductsFromCart();
        BigDecimal total = cartService.getTotal();
        total = total.setScale(2, RoundingMode.HALF_UP);
        model.addAttribute("products", productList);
        model.addAttribute("totalSpecial", cartService.specialOffer(0.8));
        model.addAttribute("total", total);

        return "cart";
    }


   @DeleteMapping("/products/{id}")
    public String deleteItemFromCart(@PathVariable("id") Long id){
       System.out.println("delete testing");
        //cartRepository.delete(cartRepository.findByProduct_ProductId(id));
        cartService.deleteItem(id);

        return "redirect:/cart-items";
    }




}
