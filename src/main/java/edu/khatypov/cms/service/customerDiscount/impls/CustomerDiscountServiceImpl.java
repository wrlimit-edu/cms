package edu.khatypov.cms.service.customerDiscount.impls;

import edu.khatypov.cms.model.CustomerDiscount;
import edu.khatypov.cms.repository.CustomerDiscountRepository;
import edu.khatypov.cms.service.customerDiscount.interfaces.ICustomerDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
                                new CustomerDiscount("Стандарт", 0, true),
                                new CustomerDiscount("Наш клиент", 5, true),
                                new CustomerDiscount("Премиум", 15, false),
                                new CustomerDiscount("Постоянный клиент", 10, true),
                                new CustomerDiscount("VIP", 20, true)
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
        return customerDiscountRepository.findAll(Sort.by(Sort.Direction.ASC, "value"));
    }

    @Override
    public CustomerDiscount getByName(String name) {
        return customerDiscountRepository.findByNameIgnoreCase(name);
    }

    @Override
    public List<CustomerDiscount> getAllByEnabled(boolean enable) {
        return customerDiscountRepository.findAllByEnabledOrderByValue(enable);
    }
}
