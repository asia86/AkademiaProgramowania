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

   /* public void  addToCart(String name, Cart cart){
        System.out.println("Czy metoda działa................");

        Integer newQuantity = (1);
        Product product = productRepository.findByName(name);
        cart.setProduct(product);
        if(cart.getAmount()!=0)
            newQuantity= newQuantity+1;
        cart.setAmount(newQuantity);
        cartRepository.save(cart);

        double total = 0.0;
        List<Cart> cartList = cartRepository.findAll();
        for(Cart item: cartList){

            total+=item.getAmount()*item.getProduct().getPrice();
        }

    }*/




    public void addProductToCart(Product product, Integer quantity){
       double total= 0.0;
       boolean bool = false;
       for(Cart item: cartRepository.findAll()){
           if(item.getProduct().getName().equals(product.getName())){
               item.setAmount(item.getAmount()+quantity);
               total+=item.getAmount()*item.getProduct().getPrice();
               item.setProduct(product);
               cartRepository.save(item);
               bool = true;
           }
       }
       if(!bool){
           Cart cart = new Cart(product, quantity);
           cartRepository.save(cart);

        }

    }

    public void deleteProductFromCart(Long id){



    }

}
