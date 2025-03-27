package com.example.productcatalogueservice.controllers;

import com.example.productcatalogueservice.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id)  {
        Product product = new Product();
        product.setId(id);
        product.setName("Product");
        product.setDescription("Product Description");
        return product;
    }
}
