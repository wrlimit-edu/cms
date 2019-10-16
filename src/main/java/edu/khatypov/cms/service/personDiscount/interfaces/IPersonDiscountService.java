package edu.khatypov.cms.service.personDiscount.interfaces;

import edu.khatypov.cms.model.CustomerDiscount;

import java.util.List;

public interface IPersonDiscountService {
    CustomerDiscount create(CustomerDiscount personDiscount);
    CustomerDiscount get(String id);
    CustomerDiscount update(CustomerDiscount personDiscount);
    CustomerDiscount delete(String id);
    List<CustomerDiscount> getAll();
}
