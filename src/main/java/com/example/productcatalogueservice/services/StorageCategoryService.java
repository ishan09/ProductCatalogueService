package com.example.productcatalogueservice.services;

import com.example.productcatalogueservice.models.Category;
import com.example.productcatalogueservice.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageCategoryService implements ICategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id).orElse(null);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category replaceCategory(Category category, Long id) {
        category.setId(id);
        return categoryRepo.save(category);
    }

    @Override
    public Boolean deleteCategory(Long id) {
        Optional<Category> categoryOptional = categoryRepo.findById(id);
        if (categoryOptional.isEmpty()) {
            return false;
        }
        categoryRepo.deleteById(id);
        return true;
    }
}
