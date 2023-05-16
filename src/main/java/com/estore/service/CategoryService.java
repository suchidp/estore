package com.estore.service;


import com.estore.model.Category;
import com.estore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    /*The method used to save  the Category */
    public Category createCategory(String name) {
        Category category = new Category();
        category.setCategoryName(name);

        return categoryRepository.save(category);
    }

    /*
     *The method used to  get the List  of All Category.
     *It also check null value.  .
     */

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }



    /*
     *The method used to  get the Category by Using  categoryId.
     */


    public Category findCategoryeById(int categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        //log.info("categoryId is " + categoryId + "  found");
        return category.orElse(null);
    }


    /*
   * The method used to  delete the Category.
   This method check  an Category is present or not   .
   */
    public void deleteCategory(int categoryId) {
        System.out.println("id" + categoryId);
        categoryRepository.deleteById(categoryId);
    }

    public Category addCategory(Category category) {

        return categoryRepository.save(category);
    }


}
