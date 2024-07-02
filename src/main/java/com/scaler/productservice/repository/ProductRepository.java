package com.scaler.productservice.repository;

import com.scaler.productservice.models.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    public Product save(Product product){
        // connect to MySQL DB
        // execute the query, insert into products () values ()
        return null;
    }

    public List<Product> getAllProducts(){
        // connect to MySQL DB
        // execute the query, select * from products
        return new ArrayList<Product>();
    }

}
