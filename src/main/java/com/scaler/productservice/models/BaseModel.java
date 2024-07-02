package com.scaler.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
// this class, itself is NOT going to be a table!
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // IDENTITY => Auto Increment
    // 1
    // 2
    // 3
    // 4
    // 5
    // last + 1
    // AUTO => automatically assign a value, it should be unique,
    // but it can be anything!
    // 1
    // 2
    // 50
    // 51
    // 109
    // 118
    // 165
    // last + randomNumber
    private Long id;
    private Date createAt;
    private Date updatedAt;
    private boolean isDeleted;
}
