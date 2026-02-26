package com.example.productcatalogueservice.controllers;

import com.example.productcatalogueservice.dtos.CategoryDto;
import com.example.productcatalogueservice.models.Category;
import com.example.productcatalogueservice.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("")
    public List<CategoryDto> getAllCategories() {
        return from(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable Long id) {
        return from(categoryService.getCategoryById(id));
    }

    @PostMapping("")
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        return from(categoryService.createCategory(from(categoryDto)));
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        return from(categoryService.replaceCategory(from(categoryDto), id));
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    private Category from(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }

    private List<CategoryDto> from(List<Category> categories) {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        categories.forEach(category -> categoryDtos.add(from(category)));
        return categoryDtos;
    }

    private CategoryDto from(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }
}
