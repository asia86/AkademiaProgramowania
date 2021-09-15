package com.example.shoppinccart.Controller;

import com.example.shoppinccart.Entity.Customer;
import com.example.shoppinccart.Entity.Items;
import com.example.shoppinccart.repository.ItemDao;
import com.example.shoppinccart.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/shopping")
public class HomeController {

    private final ItemDao shoppingCartDao;
    private CategoryService categoryService;
    Customer customer = new Customer();

    public HomeController(ItemDao shoppingCartDao, CategoryService categoryService) {
        this.shoppingCartDao = shoppingCartDao;
        this.categoryService = categoryService;
    }


    @GetMapping("/init")
    public String homepage(Model model){
        return "redirect:additemcust";
    }

    @GetMapping("/categories")
    public String getCategory(@RequestParam("name")String name, Model model){
        model.addAttribute("categ", categoryService.getCategory(name));
        model.addAttribute("products", shoppingCartDao.getItems().stream().filter(p->p.getCategory().getName().equals(name)).collect(Collectors.toList()));
        return "category";
    }

    @GetMapping("/cart")
    public String items(Model model){
        model.addAttribute("items", customer.getBasket().getBasket().entrySet());

        double price = 0.0;
        for(Map.Entry<Items, Integer> e: customer.getBasket().getBasket().entrySet()){
            price+= e.getKey().getPrice()*e.getValue();
        }
        model.addAttribute("total", price);
        return "cust_list";
    }

    @GetMapping("/additemcust")
    public String add_item_cust(Model model){
                model.addAttribute("Categories", categoryService.getAllCategories());
                model.addAttribute("Items", shoppingCartDao.getItems());
        return "cust_items";
            }

    @GetMapping("/custitemupd")
    public String add_cust_item(@RequestParam("name")String item,Model model){

        if(item!=null ){
            int pos1= shoppingCartDao.check_item(item);
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
                int pos1= shoppingCartDao.check_item(item);
                if( pos1>=0){

                    shoppingCartDao.add_prod_basket(customer, item,Integer.valueOf(quantity));
//                ModelAndView modelAndView=new ModelAndView();
                    model.addAttribute("name",item);
                }
            }catch (Exception e){
                System.out.println("Exception occurs => "+e.getMessage());
            }finally {
                return "redirect:cart";
            }

        }
        return "redirect:cart";
    }

    @GetMapping("/custitemdel")
    public String deleteItem(@RequestParam("name")String item, Model model){

        shoppingCartDao.remove_prod_basket(customer, item);
        model.addAttribute("items", customer.getBasket().getBasket().entrySet());

        return "redirect:cart";
    }

}
