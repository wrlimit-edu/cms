package edu.khatypov.cms.controller.web;

import edu.khatypov.cms.service.doc.impls.DocServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/doc")
@CrossOrigin("*")
@Controller
public class DocWebController {
    @Autowired
    DocServiceImpl docService;

    /* CREATE */

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        return "/doc/create";
    }

    /* LIST */

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("docs", docService.getAll());
        return "/doc/list";
    }

    /* SEARCH */

    @RequestMapping("/search")
    public String search(Model model) {
        return "/doc/search";
    }
}
