package edu.khatypov.cms.service.productDiscount.interfaces;

import edu.khatypov.cms.model.ProductDiscount;

import java.util.List;

public interface IProductDiscountService {
    ProductDiscount create(ProductDiscount productDiscount);
    ProductDiscount get(String id);
    ProductDiscount update(ProductDiscount productDiscount);
    ProductDiscount delete(String id);
    List<ProductDiscount> getAll();

    ProductDiscount getByName(String name);
    List<ProductDiscount> getAllByEnabled(boolean enable);
}