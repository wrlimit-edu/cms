package edu.khatypov.cms.repository;

import edu.khatypov.cms.model.ProductDiscount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDiscountRepository extends MongoRepository<ProductDiscount, String> {
    ProductDiscount findByNameIgnoreCase(String name);
    List<ProductDiscount> findAllByEnabledOrderByValue(boolean enable);
}