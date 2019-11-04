package edu.khatypov.cms.controller.web;

import edu.khatypov.cms.model.Customer;
import edu.khatypov.cms.model.Doc;
import edu.khatypov.cms.model.Product;
import edu.khatypov.cms.service.customer.impls.CustomerServiceImpl;
import edu.khatypov.cms.service.doc.impls.DocServiceImpl;
import edu.khatypov.cms.service.product.impls.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/doc")
@CrossOrigin("*")
@Controller
public class DocWebController {
    @Autowired
    DocServiceImpl docService;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    CustomerServiceImpl customerService;

    /* CREATE */

    @RequestMapping("/create")
    public String create(Model model) {
        Doc doc = docService.create(
                new Doc(
                        docService.getByMaxNumber().getNumber() + 1,
                        LocalDate.now(),
                        true,
                        true,
                        null,
                        null
                )
        );
        model.addAttribute("doc", doc);
        return "/doc/get";
    }

    /* GET */

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String get(Model model,  @PathVariable("id") String id) {
        Doc doc = docService.get(id);
        model.addAttribute("doc", doc);
        return "/doc/get";
    }

    /* LIST */

    @RequestMapping("/list")
    public String list(Model model) {
        List<Doc> list = docService.getAll();
        model.addAttribute("docs", docService.getAll());
        return "/doc/list";
    }

    /* SEARCH */

    @RequestMapping("/search")
    public String search(Model model) {
        return "/doc/search";
    }

    /* CUSTOMERS */

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    public String customers(Model model,  @PathVariable("id") String id) {
        model.addAttribute("doc", docService.get(id));
        model.addAttribute("customers", customerService.getAllByEnabled(true));
        return "/doc/customers";
    }

    /* CUSTOMER ADD */

    @RequestMapping(value = "/customerAdd/{docId}/{customerId}", method = RequestMethod.GET)
    public String customerAdd(Model model, @PathVariable("docId") String docId, @PathVariable("customerId") String customerId) {
        Doc doc = docService.get(docId);
        Customer customer = customerService.get(customerId);
        doc.setCustomer(customer);
        doc = docService.update(doc);
        model.addAttribute("doc", doc);
        return "/doc/get";
    }

    /* PRODUCTS */

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public String products(Model model,  @PathVariable("id") String id) {
        model.addAttribute("doc", docService.get(id));
        model.addAttribute("products", productService.getAll());
        return "/doc/products";
    }

    /* PRODUCT ADD */

    @RequestMapping(value = "/productAdd/{docId}/{productId}", method = RequestMethod.GET)
    public String productAdd(Model model, @PathVariable("docId") String docId, @PathVariable("productId") String productId) {
        Doc doc = docService.get(docId);
        Product product = productService.get(productId);
        if (product.getAmount() < 1) {
            String errorMessage = "Ошибка! Товара <strong>" + product.getName() + "</strong> больше нет в наличии!";
            model.addAttribute("errorMessage", errorMessage);
        } else if (doc.getProducts() != null && doc.getProducts().contains(product) == true) {
            String errorMessage = "Ошибка! Товар <strong>" + product.getName() + "</strong> уже есть в заказе!";
            model.addAttribute("errorMessage", errorMessage);
        } else {
            product.setAmount(product.getAmount() - 1);
            productService.update(product);
            product.setAmount(1);
            List<Product> list = doc.getProducts();
            if (doc.getProducts() == null) {
                list = new ArrayList<>(Arrays.asList(product));
            } else {
                list.add(product);
            }
            doc.setProducts(list);
            doc = docService.update(doc);
        }
        model.addAttribute("doc", doc);
        return "/doc/get";
    }

    /* EDIT TYPE */

    @RequestMapping(value = "/editType/{id}", method = RequestMethod.GET)
    public String editType(Model model,  @PathVariable("id") String id) {
        Doc doc = docService.get(id);
        if (doc.getType() == true) {
            doc.setType(false);
        } else {
            doc.setType(true);
        }
        docService.update(doc);
        model.addAttribute("doc", doc);
        return "/doc/get";
    }

    /* COMPLETE */

    @RequestMapping(value = "/complete/{id}", method = RequestMethod.GET)
    public String complete(Model model,  @PathVariable("id") String id) {
        Doc doc = docService.get(id);
        if (doc.getCustomer() == null) {
            String errorMessage = "Ошибка! Не выбран клиент!";
            model.addAttribute("errorMessage", errorMessage);
        } else if (doc.getProductsAmount() == 0) {
            String errorMessage = "Ошибка! В документе нет товаров!";
            model.addAttribute("errorMessage", errorMessage);
        } else {
            doc.setStatus(false);
            docService.update(doc);
            String successMessage = "Документ успешно закрыт!";
            model.addAttribute("successMessage", successMessage);
        }
        model.addAttribute("doc", doc);
        return "/doc/get";
    }

    /* PRODUCT DELETE */

    @RequestMapping(value = "/productDelete/{docId}/{productId}", method = RequestMethod.GET)
    public String productDelete(Model model, @PathVariable("docId") String docId, @PathVariable("productId") String productId) {
        Doc doc = docService.get(docId);
        Product product = productService.get(productId);
        int productIndex = doc.getProducts().indexOf(product);
        product.setAmount(product.getAmount() + docService.get(docId).getProducts().get(productIndex).getAmount());
        productService.update(product);
        doc.getProducts().remove(productIndex);
        doc = docService.update(doc);
        model.addAttribute("doc", doc);
        return "/doc/get";
    }

    /* PRODUCT PLUS */

    @RequestMapping(value = "/productPlus/{docId}/{productId}", method = RequestMethod.GET)
    public String productPlus(Model model, @PathVariable("docId") String docId, @PathVariable("productId") String productId) {
        Doc doc = docService.get(docId);
        Product product = productService.get(productId);
        if (product.getAmount() > 0) {
            product.setAmount(product.getAmount() - 1);
            productService.update(product);
            int productIndex = doc.getProducts().indexOf(product);
            doc.getProducts().get(productIndex).setAmount(doc.getProducts().get(productIndex).getAmount() + 1);
            doc = docService.update(doc);
        } else {
            String errorMessage = "Ошибка! Товара <strong>" + product.getName() + "</strong> больше нет в наличии!";
            model.addAttribute("errorMessage", errorMessage);
        }
        model.addAttribute("doc", doc);
        return "/doc/get";
    }

    /* PRODUCT MINUS */

    @RequestMapping(value = "/productMinus/{docId}/{productId}", method = RequestMethod.GET)
    public String productMinus(Model model, @PathVariable("docId") String docId, @PathVariable("productId") String productId) {
        Doc doc = docService.get(docId);
        Product product = productService.get(productId);
        product.setAmount(product.getAmount() + 1);
        productService.update(product);
        int productIndex = doc.getProducts().indexOf(product);
        if (doc.getProducts().get(productIndex).getAmount() > 1) {
            doc.getProducts().get(productIndex).setAmount(doc.getProducts().get(productIndex).getAmount() - 1);
        } else {
            doc.getProducts().remove(productIndex);
        }
        if (doc.getProducts().size() == 0) {
            doc.setProducts(null);
        }
        doc = docService.update(doc);
        model.addAttribute("doc", doc);
        return "/doc/get";
    }
}
