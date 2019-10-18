package edu.khatypov.cms.repository;

import edu.khatypov.cms.model.CustomerDiscount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDiscountRepository extends MongoRepository<CustomerDiscount, String> {
    CustomerDiscount findByNameIgnoreCase(String name);
}