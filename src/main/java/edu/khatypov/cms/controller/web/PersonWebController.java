package edu.khatypov.cms.controller.web;

import edu.khatypov.cms.forms.PersonForm;
import edu.khatypov.cms.model.Person;
import edu.khatypov.cms.service.person.impls.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/person")
@CrossOrigin("*")
@Controller
public class PersonWebController {
    @Autowired
    PersonServiceImpl personService;

    @RequestMapping("/add")
    public String add() {
        return "/person/add";
    }

    @PostMapping("/add")
    public String postAdd(Person person, Model model) {
        personService.create(person);
        return "redirect:/person/list";
    }





    /*
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model){
        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);
        return "person/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String postAdd(Model model, @ModelAttribute("personForm") PersonForm personForm) {
        Person person = new Person(
                0,
                personForm.getFirstName(),
                personForm.getMiddleName(),
                personForm.getLastName(),
                true,
                personForm.getPhone(),
                personForm.getAddress(),
                0
        );
        personService.create(person);
        return "redirect:person/list";
    }
    */





    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("persons", personService.getAll());
        return "/person/list";
    }



    /*
    @PostMapping("/country/add")
    public String postAdd(Country country, Model model) {
        String url;
        if (countryService.getByName(country.getName()) == null) {
            countryService.create(country);
            model.addAttribute("countries", countryService.getAll());
            url = "/country/list";
        } else {
            country = countryService.getByName(country.getName());
            url = "/country/add";
        }
        model.addAttribute("country", country);
        return url;
    }

    @RequestMapping("/country/edit/{id}")
    String edit(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("country", countryService.get(id));
        return "country/edit";
    }

    @PostMapping("/country/edit")
    public String postEdit(Country country, Model model) {
        countryService.update(country);

        List<City> cities = cityService.findCitiesByCountry_Id(country.getId());
        for (int i = 0; i < cities.size(); i++) {
            cities.get(i).setCountry(country);
            cityService.update(cities.get(i));
        }

        model.addAttribute("countries", countryService.getAll());
        return "/country/list";
    }
    */

}
