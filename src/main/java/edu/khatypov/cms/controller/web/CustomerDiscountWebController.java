package edu.khatypov.cms.controller.web;

import edu.khatypov.cms.service.customerDiscount.impls.CustomerDiscountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/customerDiscount")
@CrossOrigin("*")
@Controller
public class CustomerDiscountWebController {
    @Autowired
    CustomerDiscountServiceImpl customerDiscountService;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("customerDiscounts", customerDiscountService.getAll());
        return "/customerDiscount/list";
    }
}
