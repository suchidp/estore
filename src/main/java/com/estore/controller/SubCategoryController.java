package com.estore.controller;


import com.estore.config.PermitAdmin;
import com.estore.config.PermitUser;
import com.estore.exception.CategoryNotFoundException;
import com.estore.exception.SubCategoryNotFoundException;
import com.estore.model.Category;
import com.estore.model.SubCategory;
import com.estore.service.CategoryService;
import com.estore.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/subCategory")
public class SubCategoryController {


    @Autowired
    private SubCategoryService subcategoryService;

    /*  To Add new subCategory
     */
    @PostMapping()
    @PermitAdmin
    public ResponseEntity<SubCategory> addSubCategory(@RequestBody SubCategory subCategory) {
        return ResponseEntity.status(HttpStatus.OK).body(subcategoryService.saveSubcategory(subCategory));
    }

    /*
        * To get list of all SubCategory
        * @return      list of all SubCategory .

     */
       @GetMapping()
       @PermitAdmin
       @PermitUser
       public ResponseEntity<List<SubCategory>> getAllSubCategory() {
           List<SubCategory> list = subcategoryService.getAllSubCategory();
           return new ResponseEntity<List<SubCategory>>(list, HttpStatus.OK);
       }





       /*  To Delete subCategory
        * @param        subCategoryId an Id giving the subCategoryId of specific Id
        * @return      it deleted subCategoryId of specific Id.
        * @throws CategoryNotFoundException  If an Invoice of specific Id is not found
        */
    @DeleteMapping("/{subCategoryId}")
    @PermitAdmin
    public ResponseEntity<?> deleteSubCategory(@PathVariable int subCategoryId) {
        SubCategory subCategory = subcategoryService.findSubCategoryeById(subCategoryId);

        if (subCategory == null) {
            throw new SubCategoryNotFoundException("subCategory not found");
        }
        subcategoryService.deleteSubCategory(subCategoryId);

        return new ResponseEntity<String>("SubCategory Deleted successfully", HttpStatus.FOUND);
    }

    /*  To find SubCategory
     * @param  subCategoryId an Id giving the SubCategory of specific Id
     * @return      SubCategory of specific Id.
     * @throws SubCategoryNotFoundException  If an SubCategory of specific Id is not found
     */


    @GetMapping("/{subCategoryId}")
    @PermitAdmin
    public ResponseEntity<SubCategory> getSubCategory(@PathVariable int subCategoryId) throws SubCategoryNotFoundException {
        SubCategory subCategory = subcategoryService.findSubCategoryeById(subCategoryId);
        if (subCategory == null) {
            throw new SubCategoryNotFoundException("subCategory not found");
        }
        return new ResponseEntity<SubCategory>(subCategory, HttpStatus.FOUND);
    }



}
