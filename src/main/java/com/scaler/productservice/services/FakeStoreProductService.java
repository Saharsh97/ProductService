package com.scaler.productservice.services;

import com.scaler.productservice.dto.FakeStoreResponse;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    // It has to hit the APIs of FakeStore server
    RestTemplate restTemplate = new RestTemplate();
    // we use RestTemplate to call 3rd party APIs

    @Override
    public Product getSingleProduct(String productId) {
        FakeStoreResponse response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreResponse.class
        );
        Product product = new Product();
        product.setId(response.getId());
        product.setName(response.getTitle());
        product.setDescription(response.getDescription());
        product.setImageUrl(response.getImage());
        product.setPrice(response.getPrice() * 1.0);
        Category category = new Category();
        category.setName(response.getCategory());
        product.setCategory(category);

        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreResponse[] responseArray = restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                FakeStoreResponse[].class
        );
        List<Product> productsList = new ArrayList<>();
        for(FakeStoreResponse response: responseArray){
            Product product = new Product();
            product.setId(response.getId());
            product.setName(response.getTitle());
            product.setDescription(response.getDescription());
            product.setImageUrl(response.getImage());
            product.setPrice(response.getPrice() * 1.0);
            Category category = new Category();
            category.setName(response.getCategory());
            product.setCategory(category);

            productsList.add(product);
        }
        return productsList;
    }

    @Override
    public List<Product> searchProducts(String searchText) {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
