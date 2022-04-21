package net.moshi.gymlog.service;

import net.moshi.gymlog.model.User;
import net.moshi.gymlog.model.UserNotFoundException;
import net.moshi.gymlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;

    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> listAllUsers() {
        return repo.findAll();
    }

    public User save(User user) {
        return repo.save(user);
    }

    public User getById(Integer id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new UserNotFoundException("Could not find any users with ID." + id);
        }
    }

    public void deleteById(Integer id) throws UserNotFoundException {
        repo.deleteById(id);
    }
}

