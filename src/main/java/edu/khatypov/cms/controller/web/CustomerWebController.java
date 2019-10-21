package edu.khatypov.cms.controller.web;

import edu.khatypov.cms.service.customer.impls.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/customer")
@CrossOrigin("*")
@Controller
public class CustomerWebController {
    @Autowired
    CustomerServiceImpl customerService;



    /* LIST */

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("customers", customerService.getAll());
        return "/customer/list";
    }
}
