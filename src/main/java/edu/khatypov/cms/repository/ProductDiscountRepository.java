package edu.khatypov.cms.repository;

import edu.khatypov.cms.model.ProductDiscount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDiscountRepository extends MongoRepository<ProductDiscount, String> {
}