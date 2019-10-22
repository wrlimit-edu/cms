package edu.khatypov.cms.controller.web;

import edu.khatypov.cms.forms.CustomerDiscountForm;
import edu.khatypov.cms.model.CustomerDiscount;
import edu.khatypov.cms.service.customerDiscount.impls.CustomerDiscountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RequestMapping("/customerDiscount")
@CrossOrigin("*")
@Controller
public class CustomerDiscountWebController {
    @Autowired
    CustomerDiscountServiceImpl customerDiscountService;

    /* CREATE */

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        CustomerDiscountForm customerDiscountForm = new CustomerDiscountForm();
        model.addAttribute("customerDiscountForm", customerDiscountForm);
        return "/customerDiscount/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model, @ModelAttribute("customerDiscountForm") CustomerDiscountForm customerDiscountForm) {
        String url = "/customerDiscount/create";
        if (customerDiscountService.getByName(customerDiscountForm.getName()) != null) {
            model.addAttribute("customerDiscountForm", customerDiscountForm);
            model.addAttribute("errorMessage", "Ошибка! Скидка с названием <strong>" + customerDiscountForm.getName() + "</strong> уже существует!");
        } else {
            CustomerDiscount customerDiscount = new CustomerDiscount(
                    customerDiscountForm.getName(),
                    customerDiscountForm.getValue(),
                    true
            );
            customerDiscountService.create(customerDiscount);
            model.addAttribute("customerDiscounts", customerDiscountService.getAll());
            model.addAttribute("successMessage", "Скидка <strong>" + customerDiscountForm.getName() + "</strong> добавлена!");
            url = "/customerDiscount/list";
        }
        return url;
    }

    /* UPDATE */

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model,  @PathVariable("id") String id) {
        CustomerDiscount customerDiscount = customerDiscountService.get(id);
        Map<String, String> enabledMap = new LinkedHashMap<String, String>() {{
            put("true", "Включен");
            put("false", "Отключен");
        }};
        model.addAttribute("enabledMap", enabledMap);
        CustomerDiscountForm customerDiscountForm = new CustomerDiscountForm();
        customerDiscountForm.setId(id);
        customerDiscountForm.setName(customerDiscount.getName());
        customerDiscountForm.setValue(customerDiscount.getValue());
        customerDiscountForm.setEnabled(customerDiscount.isEnabled());
        model.addAttribute("customerDiscountForm", customerDiscountForm);
        return "/customerDiscount/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Model model, @ModelAttribute("customerDiscountForm") CustomerDiscountForm customerDiscountForm) {
        String url = "/customerDiscount/update";
        String errorMessage = null;
        CustomerDiscount customerDiscountByName = customerDiscountService.getByName(customerDiscountForm.getName());
        if (customerDiscountByName != null && customerDiscountByName.hashCode() != customerDiscountForm.hashCode()) {
            errorMessage = "Ошибка! Скидка с названием <strong>" + customerDiscountForm.getName() + "</strong> уже существует!";
        } else if (
                customerDiscountForm.isEnabled() == false
                && customerDiscountService.getAllByEnabled(true).size() == 1
                && customerDiscountService.getAllByEnabled(true).get(0).hashCode() == customerDiscountForm.hashCode()
        ) {
            errorMessage = "Ошибка! Минимум один вариант скидки должен быть включен!";
        }
        if (errorMessage != null) {
            Map<String, String> enabledMap = new LinkedHashMap<String, String>() {{
                put("true", "Включен");
                put("false", "Отключен");
            }};
            model.addAttribute("enabledMap", enabledMap);
            model.addAttribute("customerDiscountForm", customerDiscountForm);
            model.addAttribute("errorMessage", errorMessage);
        } else {
            CustomerDiscount customerDiscount = new CustomerDiscount(
                    customerDiscountForm.getId(),
                    customerDiscountForm.getName(),
                    customerDiscountForm.getValue(),
                    customerDiscountForm.isEnabled()
            );
            customerDiscountService.update(customerDiscount);
            model.addAttribute("customerDiscounts", customerDiscountService.getAll());
            model.addAttribute("successMessage", "Скидка <strong>" + customerDiscountForm.getName() + "</strong> изменена!");
            url = "/customerDiscount/list";
        }
        return url;
    }

    /* LIST */

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("customerDiscounts", customerDiscountService.getAll());
        return "/customerDiscount/list";
    }
}