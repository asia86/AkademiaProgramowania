package com.example.webshop.controller;

import com.example.webshop.model.Item;
import com.example.webshop.repository.ProductDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @GetMapping
    public String index(){
        return "cart/index";
    }

    @GetMapping("/buy/{id}")
    public String buy(@PathVariable("id")String id, Model model){
        ProductDao productDao = new ProductDao();
        if(model.getAttribute("cart")==null){
            List<Item> cart = new ArrayList<>();
            cart.add(new Item(productDao.find(id), 1));
            model.addAttribute("cart", cart);
        } else{
            List<Item> cart = (List<Item>)model.getAttribute("cart");
            int index = this.exists(id, cart);
            if(index==-1){
                cart.add(new Item(productDao.find(id), 1));
            }
            else{
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            model.addAttribute("cart", cart);
        }
        return "redirect:/cart/index";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id")String id, Model model){
        ProductDao productDao = new ProductDao();
        List<Item> cart = (List<Item>) model.getAttribute("cart");
        int index = this.exists(id, cart);
        cart.remove(index);
        model.addAttribute("cart", cart);
        return "redirect:/cart/index";
    }

    private int exists(String id, List<Item> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }
}
