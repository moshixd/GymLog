package net.moshi.gymlog.service;

import net.moshi.gymlog.model.Person;
import net.moshi.gymlog.model.UserNotFoundException;
import net.moshi.gymlog.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository repo;

    public PersonServiceImpl(PersonRepository repo) {
        this.repo = repo;
    }

    public List<Person> listAllPersons() {
        return repo.findAll();
    }

    public Person save(Person person) {
        return repo.save(person);
    }

    public Person getById(Integer id) throws UserNotFoundException {
        Optional<Person> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new UserNotFoundException("Could not find any Person with ID." + id);
        }
    }
}
