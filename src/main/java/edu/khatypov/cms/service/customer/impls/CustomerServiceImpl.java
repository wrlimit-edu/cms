package edu.khatypov.cms.service.customer.impls;

import edu.khatypov.cms.model.Customer;
import edu.khatypov.cms.model.CustomerDiscount;
import edu.khatypov.cms.model.Person;
import edu.khatypov.cms.repository.CustomerDiscountRepository;
import edu.khatypov.cms.repository.CustomerRepository;
import edu.khatypov.cms.service.customer.interfaces.ICustomerService;
import edu.khatypov.cms.service.customerDiscount.impls.CustomerDiscountServiceImpl;
import edu.khatypov.cms.service.person.impls.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PersonServiceImpl personService;

    @Autowired
    CustomerDiscountServiceImpl customerDiscountService;

    /* temp */
    @PostConstruct
    void init() {
        customerRepository.deleteAll();
        customerRepository.saveAll(
                new ArrayList<>(
                        Arrays.asList(
                                new Customer(
                                        personService.getAll().get(0),
                                        1,
                                        "0951234567",
                                        "Львов, Тихая 16/24",
                                        customerDiscountService.getAll().get(0)
                                ),
                                new Customer(
                                        personService.getAll().get(1),
                                        2,
                                        "0506541235",
                                        "Черновцы, Главная 245/16",
                                        customerDiscountService.getAll().get(2)
                                ),
                                new Customer(
                                        personService.getAll().get(2),
                                        33,
                                        "0974582135",
                                        "Винница, Садовая 85/31",
                                        customerDiscountService.getAll().get(1)
                                )
                        )
                )
        );
    }
    /* end temp */

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer get(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer delete(String id) {
        Customer customer = this.get(id);
        customerRepository.deleteById(id);
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll(Sort.by(Sort.Direction.ASC, "person.lastName"));
    }
}
