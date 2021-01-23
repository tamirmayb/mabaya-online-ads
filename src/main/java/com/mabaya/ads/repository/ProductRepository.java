package com.mabaya.ads.repository;

import com.mabaya.ads.model.Category;
import com.mabaya.ads.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product>getProductsByCategory(Category category);
}