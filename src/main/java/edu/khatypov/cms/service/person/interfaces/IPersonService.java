package edu.khatypov.cms.service.person.interfaces;

import edu.khatypov.cms.model.Person;

import java.util.List;

public interface IPersonService {
    Person create(Person person);
    Person get(String id);
    Person update(Person person);
    Person delete(String id);
    List<Person> getAll();
}
