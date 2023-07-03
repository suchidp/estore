package com.inventoryservice.service;

import com.inventoryservice.exception.SubCategoryNotFoundException;
import com.inventoryservice.model.SubCategory;
import com.inventoryservice.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subcategoryRepository;


    public List<SubCategory> getAllSubCategory() {
        return subcategoryRepository.findAll();
    }

    public List<SubCategory> saveSubcategories(List<SubCategory> subCategories) {
        return subcategoryRepository.saveAll(subCategories);
    }

    public List<SubCategory> getSubCategoriesByCategoryId(int categoryId) {
        return subcategoryRepository.findByCategoryCategoryId(categoryId);
    }

    public SubCategory deleteSubCategory(int subCategoryId) {
        SubCategory subCategory = subcategoryRepository.findById(subCategoryId).get();
        if (!subCategory.isArchived()) ;
        {
            subCategory.setArchived(true);
            subcategoryRepository.save(subCategory);
        }
        return subcategoryRepository.save(subCategory);
    }

    public SubCategory findSubCategoryById(int subCategoryId) {
        return subcategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new SubCategoryNotFoundException("Subcategory not found with ID: " + subCategoryId));
    }

    public SubCategory updateSubCategory(int subCategoryId, SubCategory updatedSubCategory) {
        Optional<SubCategory> existingSubCategory = subcategoryRepository.findById(subCategoryId);
        SubCategory subCategory = existingSubCategory.get();
        subCategory.setSubCategoryName(updatedSubCategory.getSubCategoryName());
        subCategory.setLastUpdatedOn(LocalDateTime.now());
        return subcategoryRepository.save(subCategory);
    }
}
