package net.moshi.gymlog.service;

import net.moshi.gymlog.model.Person;
import net.moshi.gymlog.model.User;

import java.util.List;

public interface UserService {
    List<User> listAllUsers();

    User save(User user);

    User getByEmail(String email);

    User findByUsername(String username);

    User getById(Integer id);

    void deleteById(Integer id);

    User encryptPassword(User user);

    User createUserandPerson(User user, Person person);

    User getCurrentUser();

    User updateUser(User user);

}
