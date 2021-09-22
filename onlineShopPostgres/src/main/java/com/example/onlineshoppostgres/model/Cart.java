package com.example.onlineshoppostgres.model;

import javax.persistence.*;

@Entity
@Table(name ="cart_items")
public class Cart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Product product;

    private int amount;

    public Cart(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Cart() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
