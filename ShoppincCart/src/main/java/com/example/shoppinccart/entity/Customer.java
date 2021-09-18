package com.example.shoppinccart.entity;

public class Customer {

    private String name;

    private Basket basket;

    public Customer() {
        this.basket=new Basket();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
