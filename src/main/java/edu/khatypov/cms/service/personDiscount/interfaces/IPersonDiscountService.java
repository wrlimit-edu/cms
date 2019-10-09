package edu.khatypov.cms.service.personDiscount.interfaces;

import edu.khatypov.cms.model.PersonDiscount;

import java.util.List;

public interface IPersonDiscountService {
    PersonDiscount create(PersonDiscount personDiscount);
    PersonDiscount get(String id);
    PersonDiscount update(PersonDiscount personDiscount);
    PersonDiscount delete(String id);
    List<PersonDiscount> getAll();
}
