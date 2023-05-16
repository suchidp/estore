package com.estore.service;


import com.estore.model.SubCategory;
import com.estore.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService {


    @Autowired
    private SubCategoryRepository subcategoryRepository;


    public SubCategory saveSubcategory(SubCategory subcategory) {

        return subcategoryRepository.save(subcategory);
    }


    public List<SubCategory> getAllSubCategory() {
        return subcategoryRepository.findAll();
    }


    public void deleteSubCategory(int subCategoryId) {
        System.out.println("id" + subCategoryId);
        subcategoryRepository.deleteById(subCategoryId);
    }


    public SubCategory findSubCategoryeById(int subCategoryId) {
        Optional<SubCategory> subCategory = subcategoryRepository.findById(subCategoryId);
        return subCategory.orElse(null);
    }
}
