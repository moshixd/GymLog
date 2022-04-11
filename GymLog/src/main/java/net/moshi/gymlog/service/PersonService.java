package net.moshi.gymlog.service;

import net.moshi.gymlog.model.Person;
import net.moshi.gymlog.model.Records;
import net.moshi.gymlog.repository.PersonRepository;
import net.moshi.gymlog.repository.RecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    private final RecordsRepository recorepo;

    @Autowired
    public PersonService(PersonRepository personRepository, RecordsRepository recorepo) {
        this.personRepository = personRepository;
        this.recorepo = recorepo;
    }

    public List<Person> ListAllPersons() {
        return personRepository.findAll();
    }

    public void addRecords() {
        personRepository.findById(1).get().getRecords().add(recorepo.findById(1).get());

    }
}
