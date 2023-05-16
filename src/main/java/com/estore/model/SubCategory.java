package com.estore.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "subcategory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subCategoryId;


    @Column(name = "sub_category_name")
    private String subCategoryName;



    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name="date_created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime dateCreated;



    @Column(name="last_updated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime lastUpdated; ;





    public SubCategory(int subCategoryId, String subCategoryName) {
    }
}
