package com.example.onlineshoppostgres.controller;

import com.example.onlineshoppostgres.model.Product;
import com.example.onlineshoppostgres.repository.ProductRepository;
import com.example.onlineshoppostgres.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.CallSite;

@Controller
@RequestMapping("")
public class AdminController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    public AdminController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }
//Product product = new Product();




    @GetMapping("/admin")
    public String listitems(Model model) {
        model.addAttribute("Items", productRepository.findAll());
        return "admin_product_list";
    }

    @GetMapping("/additems")
    public String add_item(Model model) {
        model.addAttribute("Item", new Product());
        return "item_form";
    }

    @PostMapping("/saveitem")
    public String saveItem(@ModelAttribute("Item")Product item){
      /*  try {
            if ((item.getName() != null && item.getPrice() != 0) && (item.getDescription() != null)) {
                if (productRepository.findByName(item.getName())!= null) {
                    return "redirect: admin";
                } else {
                    item.setName(item.getName().trim());
                    item.setPrice(item.getPrice());
                    item.setDescription(item.getDescription().trim());
                    productRepository.save(item);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurs => " + e.getMessage());
        } finally {
            return "redirect:admin";
        }
*/
    productService.addItem(item);

        return "redirect:admin";
    }

    @GetMapping("/itemupd")
    public String upd_item(@RequestParam("name")String name, Model model ){

                Product item=productRepository.findByName(name);
                model.addAttribute("Item",item);
                return "update_item";

    }

    @PostMapping("save_updateitem")
    public String update_item(@RequestParam("name")String name,@ModelAttribute("Item")Product item) {
        Product product = productRepository.getById(item.getProductId());
       // product.setProductId(item.getProductId());
        product.setName(item.getName());
        product.setPrice(item.getPrice());
       product.setDescription(item.getDescription());
       productRepository.save(product);

        return "redirect:admin";
    }
}
