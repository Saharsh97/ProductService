package com.scaler.productservice.repository;

import com.scaler.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long>{

    Product save(Product product);

    Integer countProductByPriceLessThan(Double value);

    List<Product> getProductByName(String name);
    // select count(*) from products p where p.price < value;

    List<Product> getProductByNameLikeAndDescriptionLikeOrderByPriceDesc(String nameText, String descText);
    List<Product> getProductByCategoryName(String categoryName);
    // select * from products p JOIN category c on p.category_id = c.id and c.name = categoryName

    void deleteProductById(Long id);    // hard delete -> permanent deletion of the row.
}
