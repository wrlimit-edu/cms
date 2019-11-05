package edu.khatypov.cms.service.product.interfaces;

import edu.khatypov.cms.model.Product;

import java.util.List;

public interface IProductService {
    Product create(Product product);
    Product get(String id);
    Product update(Product product);
    Product delete(String id);
    List<Product> getAll();

    Product getByMaxNumber();
    List<Product> getAllByNameIsLike(String name);
}
