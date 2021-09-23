package com.example.onlineshoppostgres.repository;

import com.example.onlineshoppostgres.model.Cart;
import com.example.onlineshoppostgres.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long > {

   // public Cart findByProductName(String name);
    public Cart findByProduct_ProductId(Long name);

    //public Product getAll();



}
