package com.example.onlineshoppostgres.service;

import com.example.onlineshoppostgres.model.Cart;
import com.example.onlineshoppostgres.model.Product;
import com.example.onlineshoppostgres.repository.CartRepository;
import com.example.onlineshoppostgres.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {
    CartRepository cartRepository;
    ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    Map<Product, Integer> productMap = new HashMap<>();

    public Map<Product, Integer> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<Product, Integer> productMap) {
        this.productMap = productMap;
    }

    public void  addToCart(String name, Cart cart, CartRepository cartRepository){
        System.out.println("Czy metoda działa................");
        Product product = cartRepository.findByProductName(name);
        if(product!=null){
            product = productRepository.findByName(name);
            //Integer newQuantity = (cart.getAmount() + 1);
            cart.setProduct(product);
            cart.setAmount(cart.getAmount()+1);

        }else {
            System.out.println("czy jeszcze działa?????????");
            product = productRepository.findByName(name);
            cart.setProduct(product);
            cart.setAmount(1);
        }

        cartRepository.save(cart);


    }




    public void addProductToCart(Product product, Integer quantity){
        if(productMap.containsKey(product)){
            productMap.put(product, productMap.get(product) + quantity);
        }else{
            productMap.put(product, quantity);
        }

    }

}
