package net.moshi.gymlog.service;

import net.moshi.gymlog.User.User;
import net.moshi.gymlog.User.UserNotFoundException;
import net.moshi.gymlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<User> listAllUsers() {
        return (List<User>) repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User getById(Integer id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if (result.isPresent()) {
            return (User) result.get();
        } else {
            throw new UserNotFoundException("Could not find any users with ID." + id);
        }
    }

    public void deleteById(Integer id) throws UserNotFoundException {
        repo.deleteById(id);
    }

}

