package com.scaler.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDTO {
//    private String id;    // you will not give the id!
    private String title;
    private Integer price;
    private String description;
    private String image;
    private String category;
}
