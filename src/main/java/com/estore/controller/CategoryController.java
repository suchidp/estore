package com.estore.controller;


import com.estore.config.PermitAdmin;
import com.estore.config.PermitUser;
import com.estore.exception.CategoryNotFoundException;
import com.estore.model.Category;
import com.estore.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /*  To Add new Category
     */
    @PostMapping()
    @PermitAdmin
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.addCategory(category));
    }



    /*
     * To get list of all Category
     * @return      list of all Category .

     */
    @GetMapping()
    @PermitAdmin
    @PermitUser
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> list = categoryService.getAllCategory();
        return new ResponseEntity<List<Category>>(list, HttpStatus.OK);
    }


    /*  To Delete Category
     * @param        categoryId an Id giving the Category of specific Id
     * @return      it deleted Category of specific Id.
     * @throws CategoryNotFoundException  If an Invoice of specific Id is not found
     */
    @DeleteMapping("/{categoryId}")
    @PermitAdmin
    public ResponseEntity<?> deleteCategory(@PathVariable int categoryId) {
        Category category = categoryService.findCategoryeById(categoryId);

        log.info("Fetching category with categoryId {}", categoryId);
        if (category == null) {
            throw new CategoryNotFoundException("category not found");
        }
        log.info("Category  of " + category.getCategoryName() + " deleted");
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<String>("Category Deleted successfully", HttpStatus.FOUND);
    }


    /*  To find Category
     * @param  subCategoryId an Id giving the categoryId of specific Id
     * @return      Category of specific Id.
     * @throws CategoryNotFoundException  If an Category of specific Id is not found
     */
    @GetMapping("/{categoryId}")
    @PermitAdmin
    @PermitUser
    public ResponseEntity<Category> getCategory(@PathVariable int categoryId) throws CategoryNotFoundException {
        Category category = categoryService.findCategoryeById(categoryId);
        if (category == null) {
            throw new CategoryNotFoundException("category not found");
        }
        return new ResponseEntity<Category>(category, HttpStatus.FOUND);
    }
}
