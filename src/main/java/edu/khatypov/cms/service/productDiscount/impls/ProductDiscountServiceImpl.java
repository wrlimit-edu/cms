package edu.khatypov.cms.service.productDiscount.impls;

import edu.khatypov.cms.model.ProductDiscount;
import edu.khatypov.cms.repository.ProductDiscountRepository;
import edu.khatypov.cms.service.productDiscount.interfaces.IProductDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductDiscountServiceImpl implements IProductDiscountService {
    @Autowired
    ProductDiscountRepository productDiscountRepository;

    /* temp */
    @PostConstruct
    void init() {
        productDiscountRepository.deleteAll();
        productDiscountRepository.saveAll(
                new ArrayList<>(
                        Arrays.asList(
                                new ProductDiscount("Нет скидки", 0, true),
                                new ProductDiscount("Сезонная распродажа", 15, false),
                                new ProductDiscount("Супер цена", 30, true),
                                new ProductDiscount("Акция", 5, true)
                        )
                )
        );
    }
    /* end temp */

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
        return productDiscountRepository.findAll(Sort.by(Sort.Direction.ASC, "value"));
    }

    @Override
    public ProductDiscount getByName(String name) {
        return productDiscountRepository.findByNameIgnoreCase(name);
    }

    @Override
    public List<ProductDiscount> getAllByEnabled(boolean enable) {
        return productDiscountRepository.findAllByEnabledOrderByValue(enable);
    }
}
