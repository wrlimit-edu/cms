package edu.khatypov.cms.service.customer.impls;

import edu.khatypov.cms.model.Customer;
import edu.khatypov.cms.repository.CustomerRepository;
import edu.khatypov.cms.service.customer.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    CustomerRepository customerRepository;

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
        return customerRepository.findAll();
    }
}
