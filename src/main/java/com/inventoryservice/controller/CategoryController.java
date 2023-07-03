package com.inventoryservice.controller;

import com.inventoryservice.config.PermitInventoryManager;
import com.inventoryservice.controller.request.CategoryRequest;
import com.inventoryservice.controller.request.SubCategoryRequest;
import com.inventoryservice.exception.CategoryNotFoundException;
import com.inventoryservice.exception.SubCategoryNotFoundException;
import com.inventoryservice.model.*;
import com.inventoryservice.service.CategoryService;
import com.inventoryservice.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubCategoryService subcategoryService;

    /*  To Add new Category*/
    @PostMapping()
    @PermitInventoryManager
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest categoryRequest) {
        Category category = categoryRequest.toEntity(categoryRequest);
        categoryService.createCategory(category);
        return new ResponseEntity<>(category, HttpStatus.FOUND);
    }

    /*  To find Category
     * @param  categoryId an Id giving the categoryId of specific Id
     * @return      Category of specific Id.
     * @throws CategoryNotFoundException  If an Category of specific Id is not found
     * */
    @GetMapping("/{categoryId}")
    public ResponseEntity<?> findCategoryById(@PathVariable int categoryId) throws CategoryNotFoundException {
        Category category = categoryService.findCategoryById(categoryId);
        if (category == null) {
            throw new CategoryNotFoundException("Category not found");
        }
        return new ResponseEntity<>(category, HttpStatus.FOUND);
    }

    /*  To Delete Category
     * @param  categoryId an Id giving the Category of specific Id
     * @return it deleted Category of specific Id.
     * @throws CategoryNotFoundException  If an Category of specific Id is not found
     */
    @DeleteMapping("/{categoryId}")
    @PermitInventoryManager
    public ResponseEntity<?> deleteCategory(@PathVariable int categoryId) throws CategoryNotFoundException {
        Category category = categoryService.findCategoryById(categoryId);
        if (category == null) {
            throw new CategoryNotFoundException("Category not found");
        }
        if (!category.isArchived()) {
            categoryService.deleteCategory(categoryId);
            return new ResponseEntity<String>("Category Deleted successfully", HttpStatus.FOUND);
        }
        return new ResponseEntity<String>("Category is archived and cannot be deleted", HttpStatus.BAD_REQUEST);
    }

    /*
     * To get list of all Category
     * @return list of all Category .
     */
    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    @PermitInventoryManager
    public ResponseEntity<?> updateCategory(
            @PathVariable int categoryId,
            @RequestBody Category category
    ) throws CategoryNotFoundException {
        Category categories = categoryService.findCategoryById(categoryId);
        if (categories == null) {
            throw new CategoryNotFoundException("Category not found");
        }
        if (!categories.isArchived()) {
            Category updatedCategory = categoryService.updateCategory(categoryId, category);
            return ResponseEntity.ok(updatedCategory);
        }
        return new ResponseEntity<>("Category is archived ", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{categoryId}/subCategory")
    @PermitInventoryManager
    public ResponseEntity<List<?>> addSubCategories(
            @PathVariable("categoryId") int categoryId,
            @RequestBody List<SubCategoryRequest> subCategoryRequests) {
        Category category = categoryService.findCategoryById(categoryId);
        List<SubCategory> subCategories = subCategoryRequests.stream()
                .map(subCategoryRequest -> {
                    SubCategory subCategory = SubCategoryRequest.toEntity(subCategoryRequest);
                    subCategory.setCategory(category);
                    return subCategory;
                })
                .collect(Collectors.toList());
        List<SubCategory> savedSubCategories = subcategoryService.saveSubcategories(subCategories);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSubCategories);
    }

    /*
     * To get list of all SubCategory
     * @return      list of all SubCategory .
     */
    @GetMapping(("/subCategory"))
    public ResponseEntity<List<SubCategory>> getAllSubCategory() {
        List<SubCategory> subCategories = subcategoryService.getAllSubCategory();
        return new ResponseEntity<>(subCategories, HttpStatus.OK);
    }

    /*  To Delete subCategory
     * @param        subCategoryId an Id giving the subCategoryId of specific Id
     * @return      it deleted subCategoryId of specific Id.
     * @throws SubCategoryNotFoundException  If an subCategory of specific Id is not found
     */
    @DeleteMapping("/{categoryId}/subCategory/{subCategoryId}")
    @PermitInventoryManager
    public ResponseEntity<?> deleteSubCategory(@PathVariable int subCategoryId) throws SubCategoryNotFoundException {
        SubCategory subCategory = subcategoryService.findSubCategoryById(subCategoryId);
        if (subCategory == null) {
            throw new SubCategoryNotFoundException("subCategory not found");
        }
        if (!subCategory.isArchived()) {
            subcategoryService.deleteSubCategory(subCategoryId);
            return new ResponseEntity<String>("subCategory Deleted successfully", HttpStatus.FOUND);
        }
        return new ResponseEntity<String>("subCategory is archived and cannot be deleted", HttpStatus.BAD_REQUEST);
    }

    /*  To find SubCategory
     * @param  subCategoryId an Id giving the SubCategory of specific Id
     * @return  SubCategory of specific Id.
     * @throws SubCategoryNotFoundException  If an SubCategory of specific Id is not found
     */
    @GetMapping("/subCategory/{subCategoryId}")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable int subCategoryId) throws SubCategoryNotFoundException {
        SubCategory subCategory = subcategoryService.findSubCategoryById(subCategoryId);
        if (subCategory == null) {
            throw new SubCategoryNotFoundException("subCategory not found");
        }
        return ResponseEntity.ok(subCategory);
    }

    @GetMapping("/{categoryId}/subCategory")
    public ResponseEntity<List<SubCategory>> getSubCategoriesByCategoryId(@PathVariable int categoryId) {
        List<SubCategory> subCategories = subcategoryService.getSubCategoriesByCategoryId(categoryId);
        return ResponseEntity.ok(subCategories);
    }

    @PutMapping("/subCategory/{subCategoryId}")
    @PermitInventoryManager
    public ResponseEntity<?> updateSubCategory(
            @PathVariable int subCategoryId, @RequestBody SubCategory subCategory
    ) throws SubCategoryNotFoundException {
        SubCategory subCategoryies = subcategoryService.findSubCategoryById(subCategoryId);
        if (subCategoryies == null) {
            throw new SubCategoryNotFoundException("SubCategory not found");
        }
        if (!subCategoryies.isArchived()) {
            SubCategory updatedSubCategory = subcategoryService.updateSubCategory(subCategoryId, subCategory);
            return ResponseEntity.ok(updatedSubCategory);
        }
        return new ResponseEntity<>("subCategory is archived ", HttpStatus.BAD_REQUEST);
    }
}








