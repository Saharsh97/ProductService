package com.scaler.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    // all these are primitive data types
    // int, String, Long, double.
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Integer t3;
    private Integer t4;
    private Integer t1;
    // This is non-primitive
    // I have to define a relation between product and category
    // => Cardinality.
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
}
