package com.example.webshop.repository;

import com.example.webshop.model.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProductDao {
    private List<Product> products;

    public ProductDao() {
        products = new ArrayList<>();
        products.add(new Product("1","Mydło", "Pieniące", new BigDecimal("25.00")));
        products.add(new Product("2", "Masło", "Się roztrzasło", new BigDecimal("99.99")));
        products.add(new Product("3","Chleb", "Razowy", new BigDecimal("5.50")));
    }


    public List<Product> all() {
        return products;
    }



    public Product byName(String name) {
        for (Product product : products) {
            if (name.equals(product.getName())) {
                return product;
            }
        }
        return null;
    }

    public Product find(String id) {
        for (Product product : products) {
            if (product.getId().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;
    }
}
