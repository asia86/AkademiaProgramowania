package com.example.onlineshoppostgres.repository;

import com.example.onlineshoppostgres.model.Category;
import com.example.onlineshoppostgres.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {




}
