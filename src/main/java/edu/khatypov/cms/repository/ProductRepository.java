package edu.khatypov.cms.repository;

import edu.khatypov.cms.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Product findTopByOrderByNumberDesc();
    List<Product> findAllByNameIsLikeIgnoreCase(String name);
}