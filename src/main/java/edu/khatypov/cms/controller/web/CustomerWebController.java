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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        if (errorMessage != null) {

        } else {
            Person person = new Person(
                    customerForm.getPerson().getFirstName(),
                    customerForm.getPerson().getMiddleName(),
                    customerForm.getPerson().getLastName(),
                    customerForm.getPerson().getGender()
            );
            person = personService.create(person);
            CustomerDiscount customerDiscount = customerDiscountService.get(customerForm.getCustomerDiscount().getId());

            Customer customer = new Customer(
                    person,
                    0,
                    customerForm.getPhone(),
                    customerForm.getAddress(),
                    customerDiscount,
                    true
            );
            personService.create(person);
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



    /* LIST */

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("customers", customerService.getAll());
        return "/customer/list";
    }
}
