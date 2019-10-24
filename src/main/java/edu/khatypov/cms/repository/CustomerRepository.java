package edu.khatypov.cms.repository;

import edu.khatypov.cms.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    List<Customer> findAllByCustomerDiscount_Id(String id);
    Customer findTopByOrderByNumberDesc();
    Customer findByPhone(String phone);
}