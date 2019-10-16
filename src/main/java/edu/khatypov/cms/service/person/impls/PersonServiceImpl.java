package edu.khatypov.cms.service.person.impls;

import edu.khatypov.cms.model.Person;
import edu.khatypov.cms.repository.PersonRepository;
import edu.khatypov.cms.service.person.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService {
    @Autowired
    PersonRepository personRepository;

    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person get(String id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public Person update(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person delete(String id) {
        Person person = this.get(id);
        personRepository.deleteById(id);
        return person;
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll(Sort.by(Sort.Direction.ASC, "lastName"));
    }
}
