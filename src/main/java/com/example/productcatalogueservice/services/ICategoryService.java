package com.example.productcatalogueservice.services;

import com.example.productcatalogueservice.models.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long id);

    List<Category> getAllCategories();

    Category createCategory(Category category);

    Category replaceCategory(Category category, Long id);

    Boolean deleteCategory(Long id);
}
