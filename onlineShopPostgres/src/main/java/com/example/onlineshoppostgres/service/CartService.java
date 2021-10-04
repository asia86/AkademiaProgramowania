package com.example.onlineshoppostgres.service;

import com.example.onlineshoppostgres.model.Cart;
import com.example.onlineshoppostgres.model.Product;
import com.example.onlineshoppostgres.repository.CartRepository;
import com.example.onlineshoppostgres.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

@Service
public class CartService {

    CartRepository cartRepository;
    ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }


    public Map<Product, Integer> getAllProductsFromCart(){

        Map<Product, Integer> productList = new HashMap<>();
        List<Cart> cartList = cartRepository.findAll();
        for(Cart item: cartList){
            productList.put(item.getProduct(), item.getAmount());

        }
        return productList;
    }

    public BigDecimal getTotal(){
        List<Cart> cartList = cartRepository.findAll();
        BigDecimal total = new BigDecimal(BigInteger.valueOf(0));
        for(Cart item: cartList){
            total= total.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getAmount())));

        }
        return total;
    }


    public void addProductToCart(Product product, Integer quantity){

       boolean bool = false;
       for(Cart item: cartRepository.findAll()){
           if(item.getProduct().getName().equals(product.getName())){
               item.setAmount(item.getAmount()+quantity);
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
        totalSpecial = totalSpecial.setScale(2, RoundingMode.HALF_UP);
        return totalSpecial;
    }

    public void deleteItem(Long id){
        cartRepository.delete(cartRepository.findByProduct_ProductId(id));
    }


}
