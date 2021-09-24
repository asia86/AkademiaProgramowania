package com.example.onlineshoppostgres.service;

import com.example.onlineshoppostgres.model.Category;
import com.example.onlineshoppostgres.model.Product;
import com.example.onlineshoppostgres.repository.ProductRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ProductServiceTest {

    private static final Product PRODUCT = new Product( "noName", BigDecimal.TEN, "description", Category.COSMETICS);
    private static final Product PRODUCT2 = new Product( "Name", BigDecimal.ONE, "description", Category.FRUITS);
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
    }

    @Test
    void testAddItem() {
        List<Product> productList = new ArrayList<>();
        Mockito.when(productRepository.findByName(any())).thenReturn(PRODUCT);
        Mockito.when(productRepository.save(any())).thenReturn(productList.add(PRODUCT));
        productService.addItem(PRODUCT);
        Assert.assertEquals(productList.get(0), PRODUCT);
    }

    @Test
    void testAddMultipleItem() {
        List<Product> productList = new ArrayList<>();
        Mockito.when(productRepository.findByName(any())).thenReturn(PRODUCT);
        product = productService.addItem(PRODUCT);
        productList.add(product);
        product = productService.addItem(new Product("milk", BigDecimal.ONE, "..", Category.FRUITS));
        productList.add(product);
        Assert.assertEquals(productList.size(), 2);
    }
    @Test
    void updateItem() {
    }
}