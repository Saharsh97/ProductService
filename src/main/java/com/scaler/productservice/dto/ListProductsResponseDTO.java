package com.scaler.productservice.dto;

import com.scaler.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListProductsResponseDTO {
    private List<Product> products;
    private String responseMessage;
}
