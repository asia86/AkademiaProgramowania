package com.example.shoppinccart.Entity;

public class Items {

    private String name;

    private Double price;

    private int stock;
    private Category category;



    public Items(){

    }

    public Items(String name, Double price, int stock, Category category) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }


    public Items(String name, Double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void add_stock(int add){
        this.stock+=add;
    }

    public void reduce_stock(int add){
        this.stock-=add;
    }

    public String getName() {
        return name;
    }

    public void setCategoryName(String category) {
        this.category.setName(category);
    }


    public Category getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
