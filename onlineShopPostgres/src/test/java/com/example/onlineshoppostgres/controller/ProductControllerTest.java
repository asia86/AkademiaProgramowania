package com.example.onlineshoppostgres.controller;

import com.example.onlineshoppostgres.model.Category;
import com.example.onlineshoppostgres.model.Product;
import com.example.onlineshoppostgres.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductControllerTest {

    @Test
    void getAllProduct() {
        //given

        ProductController productController = mock(ProductController.class);
        //when

        //then
    }

    private List<Product> prepareMockData(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("shampoo", new BigDecimal(13.5), "refresh shampoo", Category.COSMETICS));
        productList.add(new Product("milk", new BigDecimal(2.5), "delicious milk", Category.GROCERIES));
        productList.add(new Product("apple", new BigDecimal(1.3), "fresh apple", Category.FRUITS));
        return productList;
    }
}