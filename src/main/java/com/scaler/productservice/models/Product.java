package com.scaler.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseModel{
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
//    private Category category;

    // by default, every class has an empty constructor.
    // the moment you add your own constructors, the default (empty) is not usable

    // like a single row in your actual tables.

    // special methods

}
