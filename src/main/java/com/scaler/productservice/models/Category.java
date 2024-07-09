package com.scaler.productservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends BaseModel{
    private String name;
    private String parentCategory;

    // it is the inverse of the same relation between product and category
    @OneToMany(mappedBy = "category")
    // this relation is already handled by the category column, in the Product table
    private List<Product> products;
    // default behaviour for list -> lazy

}
