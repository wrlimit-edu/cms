package edu.khatypov.cms.service.customerDiscount.interfaces;

import edu.khatypov.cms.model.CustomerDiscount;

import java.util.List;

public interface ICustomerDiscountService {
    CustomerDiscount create(CustomerDiscount customerDiscount);
    CustomerDiscount get(String id);
    CustomerDiscount update(CustomerDiscount customerDiscount);
    CustomerDiscount delete(String id);
    List<CustomerDiscount> getAll();

    CustomerDiscount getByName(String name);
}
