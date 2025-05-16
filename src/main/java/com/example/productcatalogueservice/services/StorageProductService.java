package com.example.productcatalogueservice.services;

import com.example.productcatalogueservice.models.Product;
import com.example.productcatalogueservice.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product replaceProduct(Product input, Long id) {
        return productRepo.save(input);
    }

    @Override
    public Boolean deleteProduct(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isEmpty()) {
            return false;
        }
        productRepo.deleteById(id);
        return true;
    }

    @Override
    public Product createProduct(Product product) {

        Optional<Product> productOptional = productRepo.findById(product.getId());
        if(productOptional.isPresent() ) {
            return productOptional.get();
        }
        return productRepo.save(product);
    }
}
