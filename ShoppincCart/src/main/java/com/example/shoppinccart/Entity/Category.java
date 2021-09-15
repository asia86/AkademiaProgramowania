package com.example.shoppinccart.Entity;

public class Category {

    private int id;
    private final String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
