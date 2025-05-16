package com.example.productcatalogueservice.services;

import com.example.productcatalogueservice.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product replaceProduct(Product input,Long id);

    Boolean deleteProduct(Long id);

    Product createProduct(Product product);
}
