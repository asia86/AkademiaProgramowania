package com.example.webshop.controller;

import com.example.webshop.model.Item;
import com.example.webshop.model.Product;
import com.example.webshop.repository.ProductDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    ProductDao productDao;

    public CartController(ProductDao productDao) {
        this.productDao = productDao;
    }

    List<Item> cart;
    @GetMapping("/index")
    public String index(Model model) {

        model.addAttribute("cart", cart);
        model.addAttribute("newcart", new Product());
        return "index";
    }



    @RequestMapping(value = "/buy/{id}", method = RequestMethod.GET)
    public String buy(@PathVariable("id")String id, Model model){

        if(model.getAttribute("cart")==null){
             cart = new ArrayList<>();
            cart.add(new Item(productDao.find(id), 1));
            model.addAttribute("cart", cart);
            return "index";
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
        return "redirect:cart/index";
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
