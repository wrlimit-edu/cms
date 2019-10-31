package edu.khatypov.cms.service.doc.impls;

import edu.khatypov.cms.model.Doc;
import edu.khatypov.cms.model.Product;
import edu.khatypov.cms.repository.DocRepository;
import edu.khatypov.cms.service.customer.impls.CustomerServiceImpl;
import edu.khatypov.cms.service.doc.interfaces.IDocService;
import edu.khatypov.cms.service.product.impls.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DocServiceImpl implements IDocService {
    @Autowired
    DocRepository docRepository;

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    ProductServiceImpl productService;

    /* temp */
    @PostConstruct
    void init() {
        List<Doc> list = new ArrayList<>(
                Arrays.asList(
                        new Doc(
                                55,
                                LocalDate.of(2019, Month.APRIL, 25),
                                true,
                                false,
                                customerService.getAll().get(0),
                                new ArrayList<>(
                                        Arrays.asList(
                                                productService.getAll().get(0),
                                                productService.getAll().get(2),
                                                productService.getAll().get(1)
                                        )
                                )
                        ),
                        new Doc(
                                56,
                                LocalDate.of(2019, Month.MAY, 7),
                                false,
                                true,
                                customerService.getAll().get(2),
                                new ArrayList<>(
                                        Arrays.asList(
                                                productService.getAll().get(1),
                                                productService.getAll().get(2)
                                        )
                                )
                        ),
                        new Doc(
                                56,
                                LocalDate.of(2019, Month.MAY, 7),
                                true,
                                true,
                                customerService.getAll().get(3),
                                new ArrayList<>(
                                        Arrays.asList(
                                                productService.getAll().get(3),
                                                productService.getAll().get(1),
                                                productService.getAll().get(2),
                                                productService.getAll().get(0)
                                        )
                                )
                        )
                )
        );

        list.get(0).getProducts().get(0).setAmount(1);
        list.get(0).getProducts().get(1).setAmount(2);
        list.get(0).getProducts().get(2).setAmount(1);
        list.get(1).getProducts().get(0).setAmount(1);
        list.get(1).getProducts().get(1).setAmount(2);
        list.get(2).getProducts().get(0).setAmount(3);
        list.get(2).getProducts().get(1).setAmount(1);
        list.get(2).getProducts().get(2).setAmount(2);
        list.get(2).getProducts().get(3).setAmount(1);
        docRepository.deleteAll();
        docRepository.saveAll(list);
    }
    /* end temp */

    @Override
    public Doc create(Doc doc) {
        return docRepository.save(doc);
    }

    @Override
    public Doc get(String id) {
        return docRepository.findById(id).orElse(null);
    }

    @Override
    public Doc update(Doc doc) {
        return docRepository.save(doc);
    }

    @Override
    public Doc delete(String id) {
        Doc doc = this.get(id);
        docRepository.deleteById(id);
        return doc;
    }

    @Override
    public List<Doc> getAll() {
        return docRepository.findAll();
    }
}
