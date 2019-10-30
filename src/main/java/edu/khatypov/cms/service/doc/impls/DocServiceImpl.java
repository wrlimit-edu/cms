package edu.khatypov.cms.service.doc.impls;

import edu.khatypov.cms.model.Doc;
import edu.khatypov.cms.repository.DocRepository;
import edu.khatypov.cms.service.customer.impls.CustomerServiceImpl;
import edu.khatypov.cms.service.doc.interfaces.IDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DocServiceImpl implements IDocService {
    @Autowired
    DocRepository docRepository;

    @Autowired
    CustomerServiceImpl customerService;

    /* temp */
    @PostConstruct
    void init() {
        docRepository.deleteAll();
        docRepository.saveAll(
                new ArrayList<>(
                        Arrays.asList(
                                new Doc(
                                        55,
                                        LocalDate.of(2019, Month.APRIL, 25),
                                        false,
                                        true,
                                        customerService.getAll().get(0),
                                        null,
                                        254.50f
                                )
                        )
                )
        );
    }
    /* end temp */



    @Override
    public Doc create(Doc doc) {
        return docRepository.save(doc);
    }

    @Override
    public Doc get(String id) {
        return docRepository.findById(id).orElse(null);
    }

    @Override
    public Doc update(Doc doc) {
        return docRepository.save(doc);
    }

    @Override
    public Doc delete(String id) {
        Doc doc = this.get(id);
        docRepository.deleteById(id);
        return doc;
    }

    @Override
    public List<Doc> getAll() {
        return docRepository.findAll();
    }
}
