package com.example.shoppinccart.Controller;

import com.example.shoppinccart.Entity.Customer;
import com.example.shoppinccart.Entity.Items;
import com.example.shoppinccart.Entity.ShoppingCart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopping")
public class HomeController {

    ShoppingCart shoppingCart = new ShoppingCart("Shop");
    Customer customer = new Customer();
    Items item1 = new Items("Juice", 45.5, 120);
    Items item2 = new Items("Shampoo", 122.5, 520);
    Items item3 = new Items("Biscuits", 12.5, 320);

    @PostConstruct
    public void addItem() {

        shoppingCart.add_item(item1);
        shoppingCart.add_item(item2);
        shoppingCart.add_item(item3);

    }
    @GetMapping("/init")
    public String homepage(Model model){
        return "home";
    }

    @GetMapping("/shop")
    public String items(Model model){
        model.addAttribute("items", customer.getBasket().getBasket().entrySet());
        return "cust_list";
    }

    @GetMapping("/additemcust")
    public String add_item_cust(Model model){
               // Customer customer=shoppingCart.getCustomers().get(pos);
                //model.addAttribute("customer",customer);
                model.addAttribute("Items",shoppingCart.getItems());

        return "cust_items";
            }

    @GetMapping("/custitemupd")
    public String add_cust_item(@RequestParam("name")String item,Model model){

        if(item!=null ){


            int pos1=shoppingCart.check_item(item);
            if(pos1>=0){

                model.addAttribute("item",item);


            }

        }

        return "add_cust_item";

    }

    @PostMapping("/savecustprod")
    public String add_cust_item(@RequestParam("item")String item,@RequestParam("quantity")String quantity,Model model){


        if(item!=null  && quantity!=null){
            try{
                //int pos=shoppingCart.check_cust(customer);
                int pos1=shoppingCart.check_item(item);
                if( pos1>=0){

                    shoppingCart.add_prod_basket(customer, item,Integer.valueOf(quantity));
//                ModelAndView modelAndView=new ModelAndView();
                    model.addAttribute("name",item);
                    //model.addAttribute("customer",customer);

                }
            }catch (Exception e){
                System.out.println("Exception occurs => "+e.getMessage());
            }finally {
                return "redirect:shop";
            }

        }
        return "redirect:shop";
    }

    @GetMapping("/custitemdel")
    public String deleteItem(@RequestParam("name")String item, Model model){

        shoppingCart.remove_prod_basket(customer, item);
        model.addAttribute("items", customer.getBasket().getBasket().entrySet());

        return "redirect:shop";
    }

    @GetMapping("/printbill")
    public String printBill(Model model){

        double price = 0.0;
        model.addAttribute("basket", customer.getBasket().getBasket().entrySet());
        for(Map.Entry<Items, Integer> e: customer.getBasket().getBasket().entrySet()){
            price+= e.getKey().getPrice()*e.getValue();
        }
        model.addAttribute("total", price);
        return "bill";
    }
}
