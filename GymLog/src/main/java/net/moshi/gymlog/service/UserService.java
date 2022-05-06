package net.moshi.gymlog.service;

import net.moshi.gymlog.model.User;
import net.moshi.gymlog.model.UserNotFoundException;
import net.moshi.gymlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
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

    public User getByEmail(String email) throws UserNotFoundException {
        return repo.findByEmail(email);
    }

    public User findByUsername(String username) throws UserNotFoundException {
        return repo.findByEmail(username);
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

    public User getCurrentUser(Principal principal) {

        return ((User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
    }
}

