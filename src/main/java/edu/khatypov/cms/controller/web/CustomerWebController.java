package edu.khatypov.cms.controller.web;

import edu.khatypov.cms.forms.CustomerForm;
import edu.khatypov.cms.model.Customer;
import edu.khatypov.cms.model.CustomerDiscount;
import edu.khatypov.cms.model.Person;
import edu.khatypov.cms.service.customer.impls.CustomerServiceImpl;
import edu.khatypov.cms.service.customerDiscount.impls.CustomerDiscountServiceImpl;
import edu.khatypov.cms.service.person.impls.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/customer")
@CrossOrigin("*")
@Controller
public class CustomerWebController {
    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    PersonServiceImpl personService;

    @Autowired
    CustomerDiscountServiceImpl customerDiscountService;

    /* CREATE */

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        Map<String, String> genderMap = new LinkedHashMap<String, String>() {{
            put("true", "Мужской");
            put("false", "Женский");
        }};
        model.addAttribute("genderMap", genderMap);
        List<CustomerDiscount> list = customerDiscountService.getAllByEnabled(true);
        Map<String, String> customerDiscountMap = new LinkedHashMap<String, String>();
        for (int i = 0; i < list.size(); i++) {
            customerDiscountMap.put(list.get(i).getId(), list.get(i).getLongName());
        }
        model.addAttribute("customerDiscountMap", customerDiscountMap);
        CustomerForm customerForm = new CustomerForm();
        model.addAttribute("customerForm", customerForm);
        return "/customer/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model, @ModelAttribute("customerForm") CustomerForm customerForm) {
        String url = "/customer/create";
        String errorMessage = null;
        if (customerService.getByPhone(customerForm.getPhone()) != null) {
            errorMessage = "Ошибка! Номер телефона <strong>" + customerForm.getPhone() + "</strong> уже есть в базе данных!";
        }
        if (errorMessage != null) {
            Map<String, String> genderMap = new LinkedHashMap<String, String>() {{
                put("true", "Мужской");
                put("false", "Женский");
            }};
            model.addAttribute("genderMap", genderMap);
            List<CustomerDiscount> list = customerDiscountService.getAllByEnabled(true);
            Map<String, String> customerDiscountMap = new LinkedHashMap<String, String>();
            for (int i = 0; i < list.size(); i++) {
                customerDiscountMap.put(list.get(i).getId(), list.get(i).getLongName());
            }
            model.addAttribute("customerDiscountMap", customerDiscountMap);
            model.addAttribute("customerForm", customerForm);
            model.addAttribute("errorMessage", errorMessage);
            return "/customer/create";
        } else {
            Person person = new Person(
                    customerForm.getPerson().getFirstName(),
                    customerForm.getPerson().getMiddleName(),
                    customerForm.getPerson().getLastName(),
                    customerForm.getPerson().getGender()
            );
            person = personService.create(person);
            int number = customerService.getByMaxNumber().getNumber() + 1;
            CustomerDiscount customerDiscount = customerDiscountService.get(customerForm.getCustomerDiscount().getId());
            Customer customer = new Customer(
                    person,
                    number,
                    customerForm.getPhone(),
                    customerForm.getAddress(),
                    customerDiscount,
                    true
            );
            customerService.create(customer);
            model.addAttribute("customers", customerService.getAll());
            model.addAttribute("successMessage", "Клиент <strong>"
                    + customerForm.getPerson().getLastName() + " "
                    + customerForm.getPerson().getFirstName() + " "
                    + customerForm.getPerson().getMiddleName() + "</strong> добавлен!");
            url = "/customer/list";
        }
        return url;
    }

    /* UPDATE */

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model,  @PathVariable("id") String id) {
        Customer customer = customerService.get(id);
        Map<String, String> genderMap = new LinkedHashMap<String, String>() {{
            put("true", "Мужской");
            put("false", "Женский");
        }};
        model.addAttribute("genderMap", genderMap);
        List<CustomerDiscount> list = customerDiscountService.getAllByEnabled(true);
        Map<String, String> customerDiscountMap = new LinkedHashMap<String, String>();
        for (int i = 0; i < list.size(); i++) {
            customerDiscountMap.put(list.get(i).getId(), list.get(i).getLongName());
        }
        if (list.contains(customer.getCustomerDiscount()) == false) {
            customerDiscountMap.put(customer.getCustomerDiscount().getId(), customer.getCustomerDiscount().getLongName());
        }
        model.addAttribute("customerDiscountMap", customerDiscountMap);
        Map<String, String> enabledMap = new LinkedHashMap<String, String>() {{
            put("true", "Включен");
            put("false", "Отключен");
        }};
        model.addAttribute("enabledMap", enabledMap);
        CustomerForm customerForm = new CustomerForm();
        customerForm.setId(id);
        customerForm.setPerson(customer.getPerson());
        customerForm.setNumber(customer.getNumber());
        customerForm.setPhone(customer.getPhone());
        customerForm.setAddress(customer.getAddress());
        customerForm.setCustomerDiscount(customer.getCustomerDiscount());
        customerForm.setEnabled(customer.isEnabled());
        model.addAttribute("customerForm", customerForm);
        return "/customer/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Model model, @ModelAttribute("customerForm") CustomerForm customerForm) {
        String url = "/customer/update";
        String errorMessage = null;
        if (customerService.getByPhone(customerForm.getPhone()) != null && customerService.getByPhone(customerForm.getPhone()).hashCode() != customerForm.hashCode()) {
            errorMessage = "Ошибка! Номер телефона <strong>" + customerForm.getPhone() + "</strong> уже есть в базе данных!";
        }
        if (errorMessage != null) {
            Map<String, String> genderMap = new LinkedHashMap<String, String>() {{
                put("true", "Мужской");
                put("false", "Женский");
            }};
            model.addAttribute("genderMap", genderMap);
            List<CustomerDiscount> list = customerDiscountService.getAllByEnabled(true);
            Map<String, String> customerDiscountMap = new LinkedHashMap<String, String>();
            for (int i = 0; i < list.size(); i++) {
                customerDiscountMap.put(list.get(i).getId(), list.get(i).getLongName());
            }
            if (list.contains(customerForm.getCustomerDiscount()) == false) {
                customerDiscountMap.put(customerForm.getCustomerDiscount().getId(), customerForm.getCustomerDiscount().getLongName());
            }
            model.addAttribute("customerDiscountMap", customerDiscountMap);
            Map<String, String> enabledMap = new LinkedHashMap<String, String>() {{
                put("true", "Включен");
                put("false", "Отключен");
            }};
            model.addAttribute("enabledMap", enabledMap);
            model.addAttribute("customerForm", customerForm);
            model.addAttribute("errorMessage", errorMessage);
        } else {
            Person person = new Person(
                    customerForm.getPerson().getId(),
                    customerForm.getPerson().getFirstName(),
                    customerForm.getPerson().getMiddleName(),
                    customerForm.getPerson().getLastName(),
                    customerForm.getPerson().getGender()
            );
            person = personService.update(person);
            CustomerDiscount customerDiscount = customerDiscountService.get(customerForm.getCustomerDiscount().getId());
            Customer customer = new Customer(
                    customerForm.getId(),
                    person,
                    customerForm.getNumber(),
                    customerForm.getPhone(),
                    customerForm.getAddress(),
                    customerForm.getCustomerDiscount(),
                    customerForm.isEnabled()
            );
            customerService.update(customer);
            model.addAttribute("customers", customerService.getAll());
            model.addAttribute("successMessage", "Клиент <strong>"
                    + customerForm.getPerson().getLastName() + " "
                    + customerForm.getPerson().getFirstName() + " "
                    + customerForm.getPerson().getMiddleName() + "</strong> обновлен!");
            url = "/customer/list";
        }
        return url;
    }

    /* LIST */

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("customers", customerService.getAll());
        return "/customer/list";
    }

    /* SEARCH */

    @RequestMapping("/search")
    public String search(Model model) {
        return "/customer/search";
    }
}
