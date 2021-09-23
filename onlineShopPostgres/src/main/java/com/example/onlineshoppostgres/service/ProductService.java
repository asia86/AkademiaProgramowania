package com.example.onlineshoppostgres.service;

import com.example.onlineshoppostgres.model.Product;
import com.example.onlineshoppostgres.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public void  addItem(Product product){
        try {
            if ((product.getName() != null && product.getPrice() != 0) && (product.getDescription() != null)) {
                if (productRepository.findByName(product.getName()) == null) {
                    product.setName(product.getName().trim());
                    product.setPrice(product.getPrice());
                    product.setDescription(product.getDescription().trim());
                    productRepository.save(product);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurs => " + e.getMessage());
        }

    }

    public void updateItem(Product product, String name) {
        if ((product.getName() != null && product.getPrice() != 0) && (product.getDescription() != null)) {
            if (productRepository.findByName(product.getName()) == null) {
                product.setName(name);
                product.setPrice(product.getPrice());
                product.setDescription(product.getDescription().trim());
                productRepository.save(product);
            }
        }

    }
}
