package edu.khatypov.cms.service.customerDiscount.impls;

import edu.khatypov.cms.model.CustomerDiscount;
import edu.khatypov.cms.model.Person;
import edu.khatypov.cms.repository.CustomerDiscountRepository;
import edu.khatypov.cms.service.customerDiscount.interfaces.ICustomerDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerDiscountServiceImpl implements ICustomerDiscountService {
    @Autowired
    CustomerDiscountRepository customerDiscountRepository;

    /* temp */
    @PostConstruct
    void init() {
        customerDiscountRepository.deleteAll();
        customerDiscountRepository.saveAll(
                new ArrayList<>(
                        Arrays.asList(
                                new CustomerDiscount("Стандарт (нет скидки)", 0),
                                new CustomerDiscount("Наш клиент (скидка 5%)", 5),
                                new CustomerDiscount("Vip (скидка 25%)", 25),
                                new CustomerDiscount("Постоянный клиент (скидка 10%)", 10),
                                new CustomerDiscount("Премиум (скидка 20%)", 20)
                        )
                )
        );
    }
    /* end temp */

    @Override
    public CustomerDiscount create(CustomerDiscount customerDiscount) {
        return customerDiscountRepository.save(customerDiscount);
    }

    @Override
    public CustomerDiscount get(String id) {
        return customerDiscountRepository.findById(id).orElse(null);
    }

    @Override
    public CustomerDiscount update(CustomerDiscount customerDiscount) {
        return customerDiscountRepository.save(customerDiscount);
    }

    @Override
    public CustomerDiscount delete(String id) {
        CustomerDiscount customerDiscount = this.get(id);
        customerDiscountRepository.deleteById(id);
        return customerDiscount;
    }

    @Override
    public List<CustomerDiscount> getAll() {
        return customerDiscountRepository.findAll();
    }
}
