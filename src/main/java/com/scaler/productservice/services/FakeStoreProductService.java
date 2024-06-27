package com.scaler.productservice.services;

import com.scaler.productservice.dto.FakeStorePOSTResponseDTO;
import com.scaler.productservice.dto.FakeStoreRequestDTO;
import com.scaler.productservice.dto.FakeStoreResponseDTO;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class FakeStoreProductService implements ProductService{

    // It has to hit the APIs of FakeStore server
    // we use RestTemplate to call 3rd party APIs
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Product getSingleProduct(String productId) {
        FakeStoreResponseDTO response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreResponseDTO.class
        );
        // 1. you hit the API, you get back an object.
        // 2. You want to Structure the Object, into a particular format -> FakeStoreResponse.class
        // 3. convert the class structure, to its corresponding object -> response
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
        FakeStoreResponseDTO[] responseArray = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreResponseDTO[].class
        );
        List<Product> productsList = new ArrayList<>();
        for(FakeStoreResponseDTO response: responseArray){
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

    @Override
    public Product createProduct(FakeStoreRequestDTO fakeStoreRequestDTO) {
        FakeStorePOSTResponseDTO savedProductResponse = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeStoreRequestDTO,
                FakeStorePOSTResponseDTO.class
        );

        Product product = new Product();
        product.setId(savedProductResponse.getId());
        product.setName(savedProductResponse.getTitle());
        product.setDescription(savedProductResponse.getDescription());
        product.setImageUrl(savedProductResponse.getImage());
        product.setPrice(savedProductResponse.getPrice() * 1.0);
        Category category = new Category();
        category.setName(savedProductResponse.getCategory());
        product.setCategory(category);
        return product;
    }
}
