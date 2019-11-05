package edu.khatypov.cms.controller.web;

import edu.khatypov.cms.forms.ProductForm;
import edu.khatypov.cms.model.Message;
import edu.khatypov.cms.model.Product;
import edu.khatypov.cms.model.ProductDiscount;
import edu.khatypov.cms.service.product.impls.ProductServiceImpl;
import edu.khatypov.cms.service.productDiscount.impls.ProductDiscountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("message", new Message("Товар <strong>" + productForm.getName() + "</strong> добавлен!", "success"));
        return "/product/list";
    }

    /* UPDATE */

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable("id") String id) {
        Product product = productService.get(id);
        List<ProductDiscount> list = productDiscountService.getAllByEnabled(true);
        Map<String, String> productDiscountMap = new LinkedHashMap<String, String>();
        for (int i = 0; i < list.size(); i++) {
            productDiscountMap.put(list.get(i).getId(), list.get(i).getLongName());
        }
        if (list.contains(product.getProductDiscount()) == false) {
            productDiscountMap.put(product.getProductDiscount().getId(), product.getProductDiscount().getLongName());
        }
        model.addAttribute("productDiscountMap", productDiscountMap);
        ProductForm productForm = new ProductForm();
        productForm.setId(id);
        productForm.setNumber(product.getNumber());
        productForm.setName(product.getName());
        productForm.setDescription(product.getDescription());
        productForm.setPrice(product.getPrice());
        productForm.setProductDiscount(product.getProductDiscount());
        productForm.setAmount(product.getAmount());
        model.addAttribute("productForm", productForm);
        return "/product/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Model model, @ModelAttribute("person") ProductForm productForm) {
        String url = "/product/update";
        ProductDiscount productDiscount = productDiscountService.get(productForm.getProductDiscount().getId());
        Product product = new Product(
                productForm.getId(),
                productForm.getNumber(),
                productForm.getName(),
                productForm.getDescription(),
                productForm.getPrice(),
                productForm.getProductDiscount(),
                productForm.getAmount()
        );
        productService.update(product);
        model.addAttribute("products", productService.getAll());
        model.addAttribute("message", new Message("Товар <strong>" + productForm.getName() + "</strong> обновлен!", "success"));
        return "/product/list";
    }

    /* LIST */

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("products", productService.getAll());
        return "/product/list";
    }

    /* SEARCH */

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(Model model, @ModelAttribute("search") String search) {
        if (search.length() < 3) {
            model.addAttribute("products", productService.getAll());
            model.addAttribute("message", new Message("Ошибка! Для поиска введите не менее трёх символов!", "danger"));
        } else {
            List<Product> products = productService.getAllByNameIsLike(search);
            if (products.size() > 0) {
                model.addAttribute("products", products);
            } else {
                model.addAttribute("message", new Message("Ошибка! По запросу <strong>" + search + "</strong> ничего не найдено!", "danger"));
            }
        }
        return "/product/list";
    }
}
