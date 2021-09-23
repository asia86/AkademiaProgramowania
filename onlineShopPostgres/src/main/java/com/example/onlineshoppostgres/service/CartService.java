package com.example.onlineshoppostgres.service;

import com.example.onlineshoppostgres.model.Cart;
import com.example.onlineshoppostgres.model.Product;
import com.example.onlineshoppostgres.repository.CartRepository;
import com.example.onlineshoppostgres.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
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




    public void addProductToCart(Product product, Integer quantity){
        BigDecimal total = new BigDecimal(BigInteger.valueOf(0));
       boolean bool = false;
       for(Cart item: cartRepository.findAll()){
           if(item.getProduct().getName().equals(product.getName())){
               item.setAmount(item.getAmount()+quantity);
               total=total.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getAmount())));
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


    public BigDecimal specialOffer(double reduction){

        BigDecimal totalSpecial = new BigDecimal(BigInteger.valueOf(0));
        for ( Cart item : cartRepository.findAll()) {
            if(item.getAmount()>=2){
                totalSpecial=totalSpecial.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(reduction)).multiply(BigDecimal.valueOf(item.getAmount())));
            } else {
                totalSpecial=totalSpecial.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getAmount())));
            }
        }
        return totalSpecial;
    }


}
