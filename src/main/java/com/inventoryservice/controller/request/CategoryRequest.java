package com.inventoryservice.controller.request;

import com.inventoryservice.model.Category;
import com.inventoryservice.model.SubCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
public class CategoryRequest {
    private String categoryName;
    private LocalDateTime createdOn;
    private LocalDateTime lastUpdatedOn;
    private boolean isArchived;
    private List<SubCategory> subcategories;

    public static Category toEntity(CategoryRequest categoryRequest) {
        Category entity = new Category();
        entity.setCategoryName(categoryRequest.getCategoryName());
        entity.setCreatedOn(categoryRequest.getCreatedOn());
        entity.setLastUpdatedOn(categoryRequest.getLastUpdatedOn());
        entity.setArchived(categoryRequest.isArchived());
        entity.setSubcategories(categoryRequest.getSubcategories());
        return entity;
    }
}
