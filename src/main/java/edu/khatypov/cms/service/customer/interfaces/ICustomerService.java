package edu.khatypov.cms.service.customer.interfaces;

import edu.khatypov.cms.model.Customer;

import java.util.List;

public interface ICustomerService {
    Customer create(Customer customer);
    Customer get(String id);
    Customer update(Customer customer);
    Customer delete(String id);
    List<Customer> getAll();

    Customer getByMaxNumber();
    Customer getByPhone(String phone);
}
