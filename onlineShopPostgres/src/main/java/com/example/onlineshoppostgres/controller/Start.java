package com.example.onlineshoppostgres.controller;


import com.example.onlineshoppostgres.model.Category;
import com.example.onlineshoppostgres.model.Product;
import com.example.onlineshoppostgres.repository.CartRepository;
import com.example.onlineshoppostgres.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Start {
    private ProductRepository productRepository;
    private CartRepository cartRepository;

    @Autowired
    public Start(ProductRepository productRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }



    @EventListener(ApplicationReadyEvent.class)
    public void fillTable(){
        Product product1 = new Product("shampoo", new BigDecimal(13.5), "refresh shampoo", Category.COSMETICS);
        Product product2 = new Product("milk", new BigDecimal(2.5), "delicious milk", Category.GROCERIES);
        Product product3 = new Product("apple", new BigDecimal(1.3), "fresh apple", Category.FRUITS);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);



    }
}
