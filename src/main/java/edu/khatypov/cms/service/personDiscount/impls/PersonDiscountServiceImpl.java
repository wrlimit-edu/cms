package edu.khatypov.cms.service.personDiscount.impls;

import edu.khatypov.cms.model.PersonDiscount;
import edu.khatypov.cms.repository.PersonDiscountRepository;
import edu.khatypov.cms.service.personDiscount.interfaces.IPersonDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PersonDiscountServiceImpl implements IPersonDiscountService {
    @Autowired
    PersonDiscountRepository personDiscountRepository;

    /* temp */
    @PostConstruct
    void init() {
        personDiscountRepository.deleteAll();
        personDiscountRepository.saveAll(
                new ArrayList<>(
                        Arrays.asList(
                                new PersonDiscount("Нет скидки", 0),
                                new PersonDiscount("Скидка 3%", 3),
                                new PersonDiscount("Скидка 5%", 5),
                                new PersonDiscount("Скидка 7%", 7),
                                new PersonDiscount("Скидка 10%", 10),
                                new PersonDiscount("Скидка 15%", 15),
                                new PersonDiscount("Скидка 20%", 20)
                                )
                )
        );
    }
    /* end temp */

    @Override
    public PersonDiscount create(PersonDiscount personDiscount) {
        return personDiscountRepository.save(personDiscount);
    }

    @Override
    public PersonDiscount get(String id) {
        return personDiscountRepository.findById(id).orElse(null);
    }

    @Override
    public PersonDiscount update(PersonDiscount personDiscount) {
        return personDiscountRepository.save(personDiscount);
    }

    @Override
    public PersonDiscount delete(String id) {
        PersonDiscount personDiscount = this.get(id);
        personDiscountRepository.deleteById(id);
        return personDiscount;
    }

    @Override
    public List<PersonDiscount> getAll() {
        return personDiscountRepository.findAll();
    }
}
