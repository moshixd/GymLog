package net.moshi.gymlog.service;

import net.moshi.gymlog.model.Person;
import net.moshi.gymlog.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> ListAllPersons() {
        return personRepository.findAll();
    }
}
