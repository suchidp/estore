package com.inventoryservice.controller.request;

import com.inventoryservice.model.Category;
import com.inventoryservice.model.SubCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CategoryRequest {
    private String categoryName;
    private LocalDateTime createdOn;
    private LocalDateTime lastUpdatedOn;
    private boolean isArchived;
    private List<SubCategory> subcategories;

    public static Category toCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setCategoryName(categoryRequest.getCategoryName());
        category.setCreatedOn(categoryRequest.getCreatedOn());
        category.setLastUpdatedOn(categoryRequest.getLastUpdatedOn());
        category.setArchived(categoryRequest.isArchived());
        category.setSubcategories(categoryRequest.getSubcategories());
        return category;
    }
}
