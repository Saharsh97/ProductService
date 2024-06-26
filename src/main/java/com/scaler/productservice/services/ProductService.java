package com.scaler.productservice.services;

import com.scaler.productservice.dto.FakeStoreDTO;
import com.scaler.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(String productId);

    List<Product> getAllProducts();

    List<Product> searchProducts(String searchText);

    Product createProduct(Product product);

    // this is not the correct way to code.
    // why, later class.
    // for now, we want to deal with saving a FakeStore product dto
    Product createFakeStoreProduct(FakeStoreDTO fakeStoreDTO);
}
