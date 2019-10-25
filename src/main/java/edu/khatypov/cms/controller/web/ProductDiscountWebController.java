package edu.khatypov.cms.controller.web;

import edu.khatypov.cms.service.productDiscount.impls.ProductDiscountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/productDiscount")
@CrossOrigin("*")
@Controller
public class ProductDiscountWebController {
    @Autowired
    ProductDiscountServiceImpl productDiscountService;

    /* CREATE */

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        return "/productDiscount/create";
    }

    /* LIST */

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("products", productDiscountService.getAll());
        return "/productDiscount/list";
    }
}
