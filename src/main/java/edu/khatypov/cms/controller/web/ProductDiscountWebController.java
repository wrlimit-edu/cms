package edu.khatypov.cms.controller.web;

import edu.khatypov.cms.forms.ProductDiscountForm;
import edu.khatypov.cms.model.Message;
import edu.khatypov.cms.model.ProductDiscount;
import edu.khatypov.cms.service.productDiscount.impls.ProductDiscountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RequestMapping("/productDiscount")
@CrossOrigin("*")
@Controller
public class ProductDiscountWebController {
    @Autowired
    ProductDiscountServiceImpl productDiscountService;

    /* CREATE */

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        ProductDiscountForm productDiscountForm = new ProductDiscountForm();
        model.addAttribute("productDiscountForm", productDiscountForm);
        return "/productDiscount/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model, @ModelAttribute("productDiscountForm") ProductDiscountForm productDiscountForm) {
        String url = "/productDiscount/create";
        String errorMessage = null;
        if (productDiscountService.getByName(productDiscountForm.getName()) != null) {
            errorMessage = "Ошибка! Скидка с названием <strong>" + productDiscountForm.getName() + "</strong> уже существует!";
        }
        if (errorMessage != null) {
            model.addAttribute("productDiscountForm", productDiscountForm);
            model.addAttribute("message", new Message(errorMessage, "danger"));
        } else {
            ProductDiscount productDiscount = new ProductDiscount(
                    productDiscountForm.getName(),
                    productDiscountForm.getValue(),
                    true
            );
            productDiscountService.create(productDiscount);
            model.addAttribute("productDiscounts", productDiscountService.getAll());
            model.addAttribute("message", new Message("Скидка <strong>" + productDiscountForm.getName() + "</strong> добавлена!", "success"));
            url = "/productDiscount/list";
        }
        return url;
    }

    /* UPDATE */

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable("id") String id) {
        ProductDiscount productDiscount = productDiscountService.get(id);
        Map<String, String> enabledMap = new LinkedHashMap<String, String>() {{
            put("true", "Включен");
            put("false", "Отключен");
        }};
        model.addAttribute("enabledMap", enabledMap);
        ProductDiscountForm productDiscountForm = new ProductDiscountForm();
        productDiscountForm.setId(id);
        productDiscountForm.setName(productDiscount.getName());
        productDiscountForm.setValue(productDiscount.getValue());
        productDiscountForm.setEnabled(productDiscount.isEnabled());
        model.addAttribute("productDiscountForm", productDiscountForm);
        return "/productDiscount/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Model model, @ModelAttribute("productDiscountForm") ProductDiscountForm productDiscountForm) {
        String url = "/productDiscount/update";
        String errorMessage = null;

        ProductDiscount productDiscountByName = productDiscountService.getByName(productDiscountForm.getName());
        if (productDiscountByName != null && productDiscountByName.hashCode() != productDiscountForm.hashCode()) {
            errorMessage = "Ошибка! Скидка с названием <strong>" + productDiscountByName.getName() + "</strong> уже существует!";
        }
        if (errorMessage != null) {
            Map<String, String> enabledMap = new LinkedHashMap<String, String>() {{
                put("true", "Включен");
                put("false", "Отключен");
            }};
            model.addAttribute("enabledMap", enabledMap);
            model.addAttribute("productDiscountForm", productDiscountForm);
            model.addAttribute("message", new Message(errorMessage, "danger"));
        } else {
            ProductDiscount productDiscount = new ProductDiscount(
                    productDiscountForm.getId(),
                    productDiscountForm.getName(),
                    productDiscountForm.getValue(),
                    productDiscountForm.isEnabled()
            );
            productDiscountService.update(productDiscount);
            model.addAttribute("productDiscounts", productDiscountService.getAll());
            model.addAttribute("message", new Message("Скидка <strong>" + productDiscountForm.getName() + "</strong> обновлена!", "success"));
            url = "/productDiscount/list";
        }
        return url;
    }

    /* LIST */

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("productDiscounts", productDiscountService.getAll());
        return "/productDiscount/list";
    }
}
