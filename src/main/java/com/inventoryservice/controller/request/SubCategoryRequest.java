package com.inventoryservice.controller.request;

import com.inventoryservice.model.SubCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class SubCategoryRequest {
    private String subCategoryName;
    private LocalDateTime createdOn;
    private LocalDateTime lastUpdatedOn;
    private boolean isArchived;

    public static SubCategory toSubCategory(SubCategoryRequest subCategoryRequest) {
        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryName(subCategoryRequest.getSubCategoryName());
        subCategory.setCreatedOn(subCategoryRequest.getCreatedOn());
        subCategory.setLastUpdatedOn(subCategoryRequest.getLastUpdatedOn());
        subCategory.setArchived(subCategoryRequest.isArchived());
        return subCategory;
    }
}
