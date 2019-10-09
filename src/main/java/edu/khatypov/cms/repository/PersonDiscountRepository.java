package edu.khatypov.cms.repository;

import edu.khatypov.cms.model.PersonDiscount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDiscountRepository extends MongoRepository<PersonDiscount, String> {
}