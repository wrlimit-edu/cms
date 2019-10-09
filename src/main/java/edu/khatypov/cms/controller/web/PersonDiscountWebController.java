package edu.khatypov.cms.controller.web;

import edu.khatypov.cms.service.personDiscount.impls.PersonDiscountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/personDiscount")
@CrossOrigin("*")
@Controller
public class PersonDiscountWebController {
    @Autowired
    PersonDiscountServiceImpl personDiscountService;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("persons", personDiscountService.getAll());
        return "/personDiscount/list";
    }




}
