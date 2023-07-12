package com.inventoryservice.service;

import com.inventoryservice.model.Category;
import com.inventoryservice.model.SubCategory;
import com.inventoryservice.repository.CategoryRepository;
import com.inventoryservice.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subcategoryRepository;

    /*The method used to create the Category*/
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    /*The method used to list of  the Category*/
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /*
     *The method used to  get the Category by Using  categoryId.*/
    public Category findCategoryById(Integer categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        return category.orElse(null);
    }

    /*
   The method used to  delete the Category.
   This method check Category is present or not   .
   */
    public Category deleteCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
        category.setArchived(true);
        for (SubCategory subCategory : category.getSubcategories()) {
            subCategory.setArchived(true);
            subcategoryRepository.save(subCategory);
        }
        return categoryRepository.save(category);
    }

    public Category updateCategory(Integer categoryId, Category category) {
        Optional<Category> categories = categoryRepository.findById(categoryId);
        Category newCategory = categories.get();
        newCategory.setCategoryName(category.getCategoryName());
        newCategory.setLastUpdatedOn(LocalDateTime.now());
        return categoryRepository.save(newCategory);
    }
}

