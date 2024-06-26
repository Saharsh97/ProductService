package com.scaler.productservice.services;

import com.scaler.productservice.dto.FakeStoreDTO;
import com.scaler.productservice.dto.FakeStoreResponse;
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
//    @Autowired
    RestTemplate restTemplate;

    // how to make it more clear to the person reading the code,
    // that this Service depends upon another object, like RestTemplate
    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(String productId) {
        FakeStoreResponse response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreResponse.class
        );
        // I can ask this object, to give me a Product object.
        // this object needs to just fill its details and create a new Product object.
        return response.getProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreResponse[] responseArray = restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                FakeStoreResponse[].class
        );
        List<Product> productsList = new ArrayList<>();
        for(FakeStoreResponse response: responseArray){
            Product product = response.getProduct();
            productsList.add(product);
        }
        return productsList;
    }

    @Override
    public List<Product> searchProducts(String searchText) {
        FakeStoreResponse[] responseArray = restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                FakeStoreResponse[].class
        );
        List<Product> productsList = new ArrayList<>();
        for(FakeStoreResponse response: responseArray){
            Product product = response.getProduct();
            if(product.getName().toLowerCase().contains(searchText.toLowerCase())) {
                productsList.add(product);
            }
        }
        return productsList;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product createFakeStoreProduct(FakeStoreDTO fakeStoreDTO) {
        FakeStoreResponse savedObjectResponse = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeStoreDTO,
                FakeStoreResponse.class
        );
        return savedObjectResponse.getProduct();
    }


}
