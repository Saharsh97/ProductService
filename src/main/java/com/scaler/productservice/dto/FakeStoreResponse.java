package com.scaler.productservice.dto;


import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

// purpose, to store the data from outside world.
// we place this inside DTO (Data Transfer Objects)
@Getter
@Setter
public class FakeStoreResponse {
    private String id;
    private String title;
    private Integer price;
    private String description;
    private String image;
    private String category;

    public Product getProduct(){
        Product product = new Product();

        product.setId(this.id);
        product.setName(this.title);
        product.setDescription(this.description);
        product.setImageUrl(this.image);
        product.setPrice(this.price * 1.0);
        Category category = new Category();
        category.setName(this.category);
        product.setCategory(category);

        return product;
    }

}

/*
"id": 10,
    "title": "SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s",
    "price": 109,
    "description": "Easy upgrade for faster boot up, shutdown, application load and response (As compared to 5400 RPM SATA 2.5” hard drive; Based on published specifications and internal benchmarking tests using PCMark vantage scores) Boosts burst write performance, making it ideal for typical PC workloads The perfect balance of performance and reliability Read/write speeds of up to 535MB/s/450MB/s (Based on internal testing; Performance may vary depending upon drive capacity, host device, OS and application.)",
    "category": "electronics",
    "image": "https://fakestoreapi.com/img/61U7T1koQqL._AC_SX679_.jpg",
    "rating": {
        "rate": 2.9,
        "count": 470
    }
 */
