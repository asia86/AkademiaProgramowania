package com.example.shoppinccart.repository;

import com.example.shoppinccart.entity.Category;
import com.example.shoppinccart.entity.Customer;
import com.example.shoppinccart.entity.Items;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ItemDao {

    //private ShoppingCart shoppingCart;

    private List<Items> items;

    public ItemDao(List<Items> items) {
        this.items = items;
        Items item1 = new Items("Juice", 45.5, 120,new Category("1", "groceries"));
        Items item2 = new Items("Shampoo", 122.5, 520, new Category("2", "cosmetics"));
        Items item3 = new Items("Biscuits", 12.5, 320, new Category("1", "groceries"));
        Items item4 = new Items("Apple", 2.5, 120, new Category("3", "fruits"));
        Items item5 = new Items("Tomato", 3.5, 240, new Category("4", "vegetables"));
        Items item6 = new Items("Soap", 12.5, 320, new Category("2", "cosmetics"));
        this.items.add(item1);
        this.items.add(item2);
        this.items.add(item3);
        this.items.add(item4);
        this.items.add(item5);
        this.items.add(item6);

    }

    public List<Items> getItems() {
        return items;
    }

    public int check_item(String item) {
        int i = 0;
        for (Items item1 : items) {
            if (item1.getName().trim().toLowerCase().equals(item.trim().toLowerCase())) {
                return i;
            }
            i++;
        }
        return -1;

    }

    public boolean add_prod_basket(Customer cust, String item, int quantity) {

        int itemcheck = check_item(item);
        if (itemcheck >= 0) {
            Items item_add = items.get(itemcheck);
            HashMap<Items, Integer> basket = cust.getBasket().getBasket();
            if (basket.containsKey(item_add)) {
                basket.put(item_add, quantity + basket.get(item_add));
                item_add.reduce_stock(quantity);
            } else {
                basket.put(item_add, quantity);
                item_add.reduce_stock(quantity);
            }
            return true;
        } else {
            return false;
        }


    }

    public boolean remove_prod_basket(Customer cust,String item){

        // int checkcust=check_cust(customer);
        int itemcheck=check_item(item);
        if(itemcheck>=0){
            //Customer cust=this.customers.get(checkcust);
            Items item_add=items.get(itemcheck);
            HashMap<Items,Integer> basket=cust.getBasket().getBasket();
            if(basket.containsKey(item_add)){
                basket.remove(item_add);
            }
            return true;
        }else {
            return false;
        }

    }

    public boolean add_item(Items item){

        int pos=check_item(item.getName());
        if(pos<0){
            this.items.add(item);
            return true;
        }else {
            System.out.println("Item => "+item.getName()+" already exist");
        }
        return false;
    }
}
