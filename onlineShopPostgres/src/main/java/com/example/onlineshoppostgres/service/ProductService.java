package com.example.onlineshoppostgres.service;

import com.example.onlineshoppostgres.model.Cart;
import com.example.onlineshoppostgres.model.Product;
import com.example.onlineshoppostgres.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private final Product product = new Product();

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    public Product addItem(Product product){
        try {
            if (product.getName() != null  && (product.getDescription() != null)) {
                if (productRepository.findByName(product.getName()) == null) {
                    product.setName(product.getName().trim());
                    product.setPrice(product.getPrice());
                    product.setDescription(product.getDescription().trim());
                    product.setCategory(product.getCategory());
                    productRepository.save(product);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurs => " + e.getMessage());
        }
        return product;

    }

    public void updateItem(Product product, String name) {
        if ((product.getName() != null && (product.getDescription() != null))) {
                Product product1 = productRepository.getById(product.getProductId());
                product1.setName(name);
                product1.setPrice(product.getPrice());
                product1.setDescription(product.getDescription().trim());
                productRepository.save(product1);

        }

    }
}
