package com.inventoryservice.controller;

import com.inventoryservice.config.InventoryManagerAccess;
import com.inventoryservice.controller.request.CategoryRequest;
import com.inventoryservice.controller.request.CategoryResponse;
import com.inventoryservice.controller.request.SubCategoryRequest;
import com.inventoryservice.controller.request.SubCategoryResponse;
import com.inventoryservice.exception.CategoryArchivedException;
import com.inventoryservice.exception.CategoryNotFoundException;
import com.inventoryservice.exception.SubCategoryArchivedException;
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
    @InventoryManagerAccess
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest categoryRequest) {
        Category category = categoryRequest.toCategory(categoryRequest);
        categoryService.createCategory(category);
        CategoryResponse categoryResponse = CategoryResponse.fromCategory(category);
        return new ResponseEntity<>(categoryResponse, HttpStatus.FOUND);
    }

    /*  To find Category
     * @param  categoryId an Id giving the categoryId of specific Id
     * @return      Category of specific Id.
     * @throws CategoryNotFoundException  If an Category of specific Id is not found
     * */
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable Integer categoryId) throws CategoryNotFoundException {
        Category category = categoryService.findCategoryById(categoryId);
        if (category == null) {
            throw new CategoryNotFoundException("Category not found");
        }
        CategoryResponse categoryResponse = CategoryResponse.fromCategory(category);
        return new ResponseEntity<>(categoryResponse, HttpStatus.FOUND);
    }

    /*  To Delete Category
     * @param  categoryId an Id giving the Category of specific Id
     * @return it deleted Category of specific Id.
     * @throws CategoryNotFoundException  If an Category of specific Id is not found
     */
    @DeleteMapping("/{categoryId}")
    @InventoryManagerAccess
    public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId) throws CategoryNotFoundException, CategoryArchivedException {
        Category category = categoryService.findCategoryById(categoryId);
        if (category == null) {
            throw new CategoryNotFoundException("Category not found");
        }
        if (category.isArchived()) {
            throw new CategoryArchivedException("Category is archived and cannot be deleted");
        }
        categoryService.deleteCategory(categoryId);
        CategoryResponse categoryResponse = CategoryResponse.fromCategory(category);
        return new ResponseEntity<>(categoryResponse, HttpStatus.FOUND);
    }

    /*
     * To get list of all Category
     * @return list of all Category .
     */
    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryResponse> categoryResponses = categories.stream()
                .map(CategoryResponse::fromCategory)
                .collect(Collectors.toList());
        return new ResponseEntity<>(categoryResponses, HttpStatus.OK);
    }

    /*  To Update  Category */
    @PutMapping("/{categoryId}")
    @InventoryManagerAccess
    public ResponseEntity<?> updateCategory(
            @PathVariable Integer categoryId,
            @RequestBody Category category
    ) throws CategoryNotFoundException, CategoryArchivedException {
        Category categories = categoryService.findCategoryById(categoryId);
        if (categories == null) {
            throw new CategoryNotFoundException("Category not found");
        }
        if (categories.isArchived()) {
            throw new CategoryArchivedException("Category is archived and cannot be updated");
        }
        Category updatedCategory = categoryService.updateCategory(categoryId, category);
        CategoryResponse categoryResponse = CategoryResponse.fromCategory(updatedCategory);
        return new ResponseEntity<>(categoryResponse, HttpStatus.FOUND);
    }

    /*  To Add new SubCategory */
    @PostMapping("/{categoryId}/subCategory")
    @InventoryManagerAccess
    public ResponseEntity<List<SubCategoryResponse>> addSubCategories(
            @PathVariable("categoryId") Integer categoryId,
            @RequestBody List<SubCategoryRequest> subCategoryRequests) {
        Category category = categoryService.findCategoryById(categoryId);
        if (category == null) {
            throw new CategoryNotFoundException("Category not found");
        }
        List<SubCategory> subCategories = subCategoryRequests.stream()
                .map(subCategoryRequest -> {
                    SubCategory subCategory = SubCategoryRequest.toSubCategory(subCategoryRequest);
                    subCategory.setCategory(category);
                    System.out.println(subCategory);
                    return subCategory;
                })
                .collect(Collectors.toList());
        List<SubCategory> savedSubCategories = subcategoryService.saveSubcategories(subCategories);
        List<SubCategoryResponse> subCategoryResponses = savedSubCategories.stream()
                .map(SubCategoryResponse::fromSubcategory)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(subCategoryResponses);
    }

    /*
     * To get list of all SubCategory
     * @return      list of all SubCategory .
     */
    @GetMapping(("/subCategory"))
    public ResponseEntity<List<SubCategoryResponse>> getAllSubCategory() {
        List<SubCategory> subCategories = subcategoryService.getAllSubCategory();
        List<SubCategoryResponse> subCategoryResponse = subCategories.stream()
                .map(SubCategoryResponse::fromSubcategory)
                .collect(Collectors.toList());
        return new ResponseEntity<>(subCategoryResponse, HttpStatus.OK);

    }
    /*
      The method used to  delete the SubCategory.
      This method  also check SubCategory is present or not   .
      */
    @DeleteMapping("/{categoryId}/subCategory/{subCategoryId}")
    @InventoryManagerAccess
    public ResponseEntity<?> deleteSubCategory(@PathVariable Integer subCategoryId) throws SubCategoryNotFoundException, SubCategoryArchivedException {
        SubCategory subCategory = subcategoryService.findSubCategoryById(subCategoryId);
        if (subCategory == null) {
            throw new SubCategoryNotFoundException("subCategory not found");
        }
        if (subCategory.isArchived()) {
            throw new SubCategoryArchivedException("subCategory is archived and cannot be deleted");
        }
        SubCategory deletedSubcategory = subcategoryService.deleteSubCategory(subCategoryId);
        SubCategoryResponse subCategoryResponse = SubCategoryResponse.fromSubcategory(deletedSubcategory);
        return new ResponseEntity<>(subCategoryResponse, HttpStatus.FOUND);
    }

    /*  To find subCategory
     * @param  subCategoryId an Id giving the subCategory of specific Id
     * @return      subCategory of specific Id.
     * @throws SubCategoryNotFoundException  If an subCategory of specific Id is not found
     * */
    @GetMapping("/subCategory/{subCategoryId}")
    public ResponseEntity<SubCategoryResponse> getSubCategoryById(@PathVariable Integer subCategoryId) throws SubCategoryNotFoundException {
        SubCategory subCategory = subcategoryService.findSubCategoryById(subCategoryId);
        if (subCategory == null) {
            throw new SubCategoryNotFoundException("SubCategory not found");
        }
        SubCategoryResponse subCategoryResponse = SubCategoryResponse.fromSubcategory(subCategory);
        return new ResponseEntity<>(subCategoryResponse, HttpStatus.FOUND);
    }

    /*  To find subCategories of specific Category
     * @param  categoryId an Id giving the List of  subCategory .
     * @return      List<SubCategoryResponse> .
     * @throws CategoryNotFoundException  If an Category of specific Id is not found
     * */
    @GetMapping("/{categoryId}/subCategory")
    public ResponseEntity<List<SubCategoryResponse>> getSubCategoriesByCategoryId(@PathVariable Integer categoryId, Category category) throws CategoryNotFoundException {
        Category categories = categoryService.findCategoryById(categoryId);
        if (categories == null) {
            throw new CategoryNotFoundException("Category not found");
        }
        List<SubCategory> subCategories = subcategoryService.getSubCategoryByCategoryId(categories);
        List<SubCategoryResponse> subCategoryResponse = subCategories.stream()
                .map(SubCategoryResponse::fromSubcategory)
                .collect(Collectors.toList());
        return new ResponseEntity<>(subCategoryResponse, HttpStatus.OK);
    }

    /*  To Update  SubCategory */
    @PutMapping("/subCategory/{subCategoryId}")
    @InventoryManagerAccess
    public ResponseEntity<?> updateSubCategory(
            @PathVariable Integer subCategoryId, @RequestBody SubCategory subCategory
    ) throws SubCategoryNotFoundException, SubCategoryArchivedException {
        SubCategory subCategoryies = subcategoryService.findSubCategoryById(subCategoryId);
        if (subCategoryies == null) {
            throw new SubCategoryNotFoundException("SubCategory not found");
        }
        if (subCategory.isArchived()) {
            throw new SubCategoryArchivedException("subCategory is archived and cannot be updated");
        }
        SubCategory updatedSubCategory = subcategoryService.updateSubCategory(subCategoryId, subCategory);
        SubCategoryResponse subCategoryResponse = SubCategoryResponse.fromSubcategory(updatedSubCategory);
        return new ResponseEntity<>(subCategoryResponse, HttpStatus.FOUND);
    }
}








