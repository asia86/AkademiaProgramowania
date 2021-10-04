package com.example.onlineshoppostgres.service;
import com.example.onlineshoppostgres.exception.BadPriceFormat;
import com.example.onlineshoppostgres.model.Product;
import com.example.onlineshoppostgres.repository.ProductRepository;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    public Product addItem(Product product){
        try {
            if (product.getName() != null  && (product.getDescription() != null)) {
                    product.setName(product.getName().trim());
                    product.setPrice(product.getPrice());
                    product.setDescription(product.getDescription().trim());
                    product.setCategory(product.getCategory());
                    productRepository.save(product);
            }
        } catch (Exception e) {
            System.out.println("Exception occurs => " + e.getMessage());
        }
        return product;

    }


}
