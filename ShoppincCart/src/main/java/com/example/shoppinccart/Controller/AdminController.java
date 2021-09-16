package com.example.shoppinccart.Controller;

import com.example.shoppinccart.Entity.Category;
import com.example.shoppinccart.Entity.Items;
import com.example.shoppinccart.repository.ItemDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "product_list";
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

}
