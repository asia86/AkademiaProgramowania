package com.example.shoppinccart.controller;

import com.example.shoppinccart.entity.Items;
import com.example.shoppinccart.repository.ItemDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ItemDao itemDao;

    public AdminController(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @GetMapping("/index")
    public String listitems(Model model) {
        model.addAttribute("Items", itemDao.getItems());
        return "admin_product_list";
    }

    @GetMapping("/additems")
    public String add_item(Model model) {
           model.addAttribute("Item", new Items());
        return "item_form";
    }


    @PostMapping("saveitem")
    public String save_item(@ModelAttribute("Item") Items item) {
        try {
            if ((item.getName() != null && item.getPrice() != 0) && (item.getStock() != 0)) {
                int pos = itemDao.check_item(item.getName());
                System.out.println(pos);
                if (pos >= 0) {
                    Items items = itemDao.getItems().get(pos);
                    items.setName(item.getName().trim());
                    items.setPrice(item.getPrice());
                    items.setStock(item.getStock());
                    //items.setCategoryName(item.getCategory().getName());
                    return "redirect:index";
                } else {
                    itemDao.add_item(item);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurs => " + e.getMessage());
        } finally {
            return "redirect:index";
        }
    }

    @GetMapping("/itemupd")
    public String upd_item(@RequestParam("name")String name, Model model ){

        if(name!=null){

            int pos=itemDao.check_item(name);
            if(pos>=0){

                Items item=itemDao.getItems().get(pos);
                model.addAttribute("Item",item);
                return "update_item";
            }
        }

        return "redirect:index";
    }

    @PostMapping("updateitem")
    public String update_item(@RequestParam("name")String[] name,@ModelAttribute("Item")Items item){
        if ((item.getName() != null && item.getPrice() != 0) && (item.getStock() != 0)) {
            int pos = itemDao.check_item(name[0]);
            if (pos >= 0) {
                Items items = itemDao.getItems().get(pos);
                items.setName(name[1]);
                items.setPrice(item.getPrice());
                items.setStock(item.getStock());
//                System.out.println(item.toString());
                return "redirect:index";
            } else {
                itemDao.add_item(item);
            }
        }
        return "redirect:index";
    }

    @GetMapping("/itemdel")
    public String del_item(@RequestParam("name")String name){
        if(name!=null){

            int pos=itemDao.check_item(name);
            if(pos>=0){
                itemDao.getItems().remove(pos);
            }
        }
        return "redirect:index";
    }

}
