package com.example.shoppinccart.repository;


import com.example.shoppinccart.entity.Category;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDao {
    private List<Category> categories = new ArrayList<>();


    public CategoryDao() {
        Category cat1 = new Category("1", "groceries");
        Category cat2 = new Category("2", "cosmetics");
        Category cat3 = new Category("3", "fruits");
        Category cat4 = new Category("3", "vegetables");
        categories.add(cat1);
        categories.add(cat2);
        categories.add(cat3);
        categories.add(cat4);
    }

    public List<Category> getAllCategories() {
        return categories;
    }

    public Category getCategory(String name) {
        return categories.stream().filter(c -> c.getName().equals(name)).findFirst().get();
    }

}


