package edu.khatypov.cms.service.personDiscount.impls;

import edu.khatypov.cms.model.CustomerDiscount;
import edu.khatypov.cms.service.personDiscount.interfaces.IPersonDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PersonDiscountServiceImpl implements IPersonDiscountService {
    @Autowired
    PersonDiscountRepository personDiscountRepository;

    /* temp */
    @PostConstruct
    void init() {
        personDiscountRepository.deleteAll();
        personDiscountRepository.saveAll(
                new ArrayList<>(
                        Arrays.asList(
                                new CustomerDiscount("Нет скидки", 0),
                                new CustomerDiscount("Скидка 3%", 3),
                                new CustomerDiscount("Скидка 5%", 5),
                                new CustomerDiscount("Скидка 7%", 7),
                                new CustomerDiscount("Скидка 10%", 10),
                                new CustomerDiscount("Скидка 15%", 15),
                                new CustomerDiscount("Скидка 20%", 20)
                                )
                )
        );
    }
    /* end temp */

    @Override
    public CustomerDiscount create(CustomerDiscount personDiscount) {
        return personDiscountRepository.save(personDiscount);
    }

    @Override
    public CustomerDiscount get(String id) {
        return personDiscountRepository.findById(id).orElse(null);
    }

    @Override
    public CustomerDiscount update(CustomerDiscount personDiscount) {
        return personDiscountRepository.save(personDiscount);
    }

    @Override
    public CustomerDiscount delete(String id) {
        CustomerDiscount personDiscount = this.get(id);
        personDiscountRepository.deleteById(id);
        return personDiscount;
    }

    @Override
    public List<CustomerDiscount> getAll() {
        return personDiscountRepository.findAll();
    }
}
