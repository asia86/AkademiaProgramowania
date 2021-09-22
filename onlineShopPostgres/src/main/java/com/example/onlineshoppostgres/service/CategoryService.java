package com.example.onlineshoppostgres.service;

import com.example.onlineshoppostgres.model.Category;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService {
   private List<Category> categoryList = Arrays.asList(Category.values());


    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }



    public String getCategoryByName(String categoryName){
        for(Category cat: this.categoryList){
            if(cat.name().equals(categoryName))
                return cat.name();

        }
        return  null;
    }

}
