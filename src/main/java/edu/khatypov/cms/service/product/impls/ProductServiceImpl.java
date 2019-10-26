package edu.khatypov.cms.service.product.impls;

import edu.khatypov.cms.model.Product;
import edu.khatypov.cms.repository.ProductRepository;
import edu.khatypov.cms.service.product.interfaces.IProductService;
import edu.khatypov.cms.service.productDiscount.impls.ProductDiscountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductDiscountServiceImpl productDiscountService;

    /* temp */
    @PostConstruct
    void init() {
        productRepository.deleteAll();
        productRepository.saveAll(
                new ArrayList<>(
                        Arrays.asList(
                                new Product(
                                        123,
                                        "Куртка",
                                        "Осенняя ветровка средней плотности",
                                        845.50f,
                                        productDiscountService.getAll().get(0),
                                        10
                                ),
                                new Product(
                                        124,
                                        "Штаны",
                                        "Чёрные классические брюки",
                                        425f,
                                        productDiscountService.getAll().get(1),
                                        15
                                ),
                                new Product(
                                        125,
                                        "Джинсы",
                                        "Прямые не тёртые, 100% хлопок",
                                        480.30f,
                                        productDiscountService.getAll().get(2),
                                        7
                                ),
                                new Product(
                                        126,
                                        "Футболка",
                                        "Тонкая белая с рисунком",
                                        95.99f,
                                        productDiscountService.getAll().get(0),
                                        23
                                ),
                                new Product(
                                        127,
                                        "Кофта",
                                        "Зелёная на байке с капюшоном",
                                        360f,
                                        productDiscountService.getAll().get(0),
                                        11
                                )
                        )
                )
        );
    }
    /* end temp */

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