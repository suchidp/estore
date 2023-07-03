package com.inventoryservice.controller.request;

import com.inventoryservice.model.Category;
import com.inventoryservice.model.SubCategory;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class SubCategoryRequest {
    private String subCategoryName;
    private Category category;
    private LocalDateTime createdOn;
    private LocalDateTime lastUpdatedOn;
    private boolean isArchived;

    public static SubCategory toEntity(SubCategoryRequest subCategoryRequest) {
        SubCategory entity = new SubCategory();
        entity.setSubCategoryName(subCategoryRequest.getSubCategoryName());
        entity.setCreatedOn(subCategoryRequest.getCreatedOn());
        entity.setLastUpdatedOn(subCategoryRequest.getLastUpdatedOn());
        entity.setArchived(subCategoryRequest.isArchived());
        entity.setCategory(subCategoryRequest.getCategory());
        return entity;
    }
}
