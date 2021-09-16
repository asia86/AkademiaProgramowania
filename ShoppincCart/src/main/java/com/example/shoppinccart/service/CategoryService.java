package com.example.shoppinccart.service;


import com.example.shoppinccart.Entity.Category;
import com.example.shoppinccart.Entity.Items;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService {
    private List<Category> categories = new ArrayList<>();


    public CategoryService() {
        Category cat1 = new Category("1", "groceries");
        Category cat2 = new Category("2", "cosmetics");
        Category cat3 = new Category("3", "fruits");
        Category cat4 = new Category("3", "vegetables");
        categories.add(cat1);
        categories.add(cat2);
        categories.add(cat3);
        categories.add(cat4);
    }

    public List<Category> getAllCategories(){
        return categories;
    }

    public Category getCategory(String name){
        return categories.stream().filter(c -> c.getName().equals(name)).findFirst().get();
    }

  /*  public List<Items> findProducts(String name){
        List<Items> products = new ArrayList<>();
        for(Items i: shoppingCartDao.getItems()){
            if(name.equals(i.getCategory())){
                products.add(i);
            }
        }
    }*/


}
