package edu.khatypov.cms.service.product.impls;

import edu.khatypov.cms.model.Product;
import edu.khatypov.cms.repository.ProductRepository;
import edu.khatypov.cms.service.product.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product get(String id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product delete(String id) {
        Product product = this.get(id);
        productRepository.deleteById(id);
        return product;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}