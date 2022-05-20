package net.moshi.gymlog.service;

import lombok.extern.slf4j.Slf4j;
import net.moshi.gymlog.model.Person;
import net.moshi.gymlog.model.User;
import net.moshi.gymlog.model.UserNotFoundException;
import net.moshi.gymlog.repository.UserRepository;
import net.moshi.gymlog.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository repo;

    @Autowired
    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> listAllUsers() {
        return repo.findAll();
    }

    public User save(User user) {
        return repo.save(user);
    }

    public User getByEmail(String email) {
        return repo.findByEmail(email);
    }

    public User findByUsername(String username) {
        return repo.findByEmail(username);
    }

    public User getById(Integer id) {
       return repo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Could not find any users with ID." + id));
        }


    public void deleteById(Integer id) {
        boolean exist = repo.findById(id).isPresent();
        if(exist) {
            repo.deleteById(id);
        } else throw new UsernameNotFoundException("Could not find any users with ID." + id);
    }



    @Override
    public User encryptPassword(User user) {
        return null;
    }


    @Override
    public User createUserandPerson(User user, Person person) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setPerson(person);
        User registeredUser = save(user);
        return null;
    }

    @Override
    public User getCurrentUser() {
        String email = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            email = ((CustomUserDetails) principal).getEmail();
        } else {
            email = principal.toString();
        }
        User user = getByEmail(email);
        return user;
    }

    @Override
    public User updateUser(User user) {
        User updatedUser = getCurrentUser();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setPassword(encoder.encode(user.getPassword()));
        return repo.save(updatedUser);
    }
}

