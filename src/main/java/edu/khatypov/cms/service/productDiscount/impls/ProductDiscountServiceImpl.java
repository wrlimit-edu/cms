package edu.khatypov.cms.service.productDiscount.impls;

import edu.khatypov.cms.model.ProductDiscount;
import edu.khatypov.cms.repository.ProductDiscountRepository;
import edu.khatypov.cms.service.productDiscount.interfaces.IProductDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDiscountServiceImpl implements IProductDiscountService {
    @Autowired
    ProductDiscountRepository productDiscountRepository;

    @Override
    public ProductDiscount create(ProductDiscount productDiscount) {
        return productDiscountRepository.save(productDiscount);
    }

    @Override
    public ProductDiscount get(String id) {
        return productDiscountRepository.findById(id).orElse(null);
    }

    @Override
    public ProductDiscount update(ProductDiscount productDiscount) {
        return productDiscountRepository.save(productDiscount);
    }

    @Override
    public ProductDiscount delete(String id) {
        ProductDiscount productDiscount = this.get(id);
        productDiscountRepository.deleteById(id);
        return productDiscount;
    }

    @Override
    public List<ProductDiscount> getAll() {
        return productDiscountRepository.findAll();
    }
}
