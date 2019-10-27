package edu.khatypov.cms.controller.web;

import edu.khatypov.cms.forms.ProductForm;
import edu.khatypov.cms.model.Product;
import edu.khatypov.cms.model.ProductDiscount;
import edu.khatypov.cms.service.product.impls.ProductServiceImpl;
import edu.khatypov.cms.service.productDiscount.impls.ProductDiscountServiceImpl;
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

@RequestMapping("/product")
@CrossOrigin("*")
@Controller
public class ProductWebController {
    @Autowired
    ProductServiceImpl productService;

    @Autowired
    ProductDiscountServiceImpl productDiscountService;

    /* CREATE */

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        List<ProductDiscount> list = productDiscountService.getAllByEnabled(true);
        Map<String, String> productDiscountMap = new LinkedHashMap<String, String>();
        for (int i = 0; i < list.size(); i++) {
            productDiscountMap.put(list.get(i).getId(), list.get(i).getLongName());
        }
        model.addAttribute("productDiscountMap", productDiscountMap);
        ProductForm productForm = new ProductForm();
        model.addAttribute("productForm", productForm);
        return "/product/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model, @ModelAttribute("productForm") ProductForm productForm) {
        String url = "/product/create";
        String errorMessage = null;
        if (errorMessage != null) {
            /* >>>>>>>>>>>>>>>>>>>>>>>>>>> */
        } else {
            int number = productService.getByMaxNumber().getNumber() + 1;
            ProductDiscount productDiscount = productDiscountService.get(productForm.getProductDiscount().getId());
            Product product = new Product(
                    number,
                    productForm.getName(),
                    productForm.getDescription(),
                    productForm.getPrice(),
                    productDiscount,
                    productForm.getAmount()
            );
            productService.create(product);
            model.addAttribute("products", productService.getAll());
            model.addAttribute("successMessage", "Товар <strong>" + productForm.getName() + "</strong> добавлен!");
            url = "/product/list";
        }
        return url;
    }

    /* LIST */

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("products", productService.getAll());
        return "/product/list";
    }

    /* SEARCH */

    @RequestMapping("/search")
    public String search(Model model) {
        return "/product/search";
    }
}
