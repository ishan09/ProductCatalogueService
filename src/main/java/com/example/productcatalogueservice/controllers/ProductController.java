package com.example.productcatalogueservice.controllers;

import com.example.productcatalogueservice.dtos.CategoryDto;
import com.example.productcatalogueservice.dtos.ProductDto;
import com.example.productcatalogueservice.models.Category;
import com.example.productcatalogueservice.models.Product;
import com.example.productcatalogueservice.services.IProductService;
import com.example.productcatalogueservice.services.StorageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    @Qualifier("storageProductService")
    private IProductService productService;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts()  {
        return from(productService.getAllProducts());
    }

    @GetMapping("/products/{id}")
    public ProductDto getProduct(@PathVariable Long id)  {
        return from(productService.getProductById(id));
    }

    @PostMapping("")
    public ProductDto createProduct(@RequestBody ProductDto productDto)  {
        return from(productService.createProduct(from(productDto)));
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto)  {
        return from(productService.replaceProduct(from(productDto),id));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id)  {
        productService.deleteProduct(id);
    }

    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        return product;
    }

    private List<ProductDto> from(List<Product> products) {
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(product -> {productDtos.add(from(product));});
        return productDtos;
    }
    private ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if (product.getCategory() != null){
            CategoryDto categoryDto = new CategoryDto();
            Category category = product.getCategory();
            categoryDto.setName(category.getName());
            categoryDto.setDescription(category.getDescription());
            categoryDto.setId(category.getId());
        }

        return productDto;
    }

}
