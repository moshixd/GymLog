package net.moshi.gymlog.service;

import net.moshi.gymlog.model.Person;
import net.moshi.gymlog.model.UserNotFoundException;

import java.util.List;

public interface PersonService {

    public List<Person> listAllPersons();

    public Person save(Person person);

    public Person getById(Integer id) throws UserNotFoundException;
}
