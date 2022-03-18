package net.moshi.gymlog.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public UserService() {
    }

    public List<User> listAllUsers() {
        return this.repo.findAll();
    }

    public void save(User user) {
        this.repo.save(user);
    }

    public User getById(Long id) throws UsernameNotFoundException {
        Optional<User> result = this.repo.findById(id);
        if (result.isPresent()) {
            return (User) result.get();
        } else {
            throw new UsernameNotFoundException("Could not find any users with ID." + id);
        }
    }

    public void deleteById(Long id) {
        this.repo.deleteById(id);
    }
}

