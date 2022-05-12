package net.moshi.gymlog.controller;

import net.moshi.gymlog.model.Person;
import net.moshi.gymlog.model.User;
import net.moshi.gymlog.model.UserNotFoundException;
import net.moshi.gymlog.repository.UserRepository;
import net.moshi.gymlog.security.CustomUserDetails;
import net.moshi.gymlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    private User getCurrentUser() throws UserNotFoundException {
        String email = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            email = ((CustomUserDetails) principal).getEmail();
        } else {
            email = principal.toString();
        }
        User user = userService.getByEmail(email);
        return user;
    }

    @GetMapping({"/log"})
    public String loginUser( ) throws UserNotFoundException {
        return "redirect:/list_user/" + getCurrentUser().getEmail();
    }

    @GetMapping({"list_user/{email}"})
    public String viewUser(@PathVariable("email") String email, Model model) throws UserNotFoundException {
        User user = userService.getByEmail(email);
        Person person = user.getPerson();
        model.addAttribute("foundUser", user);
        model.addAttribute("foundperson", person);
        return "user";
    }

    @GetMapping({"/list_users"})
    public String viewUsersList(Model model) {
        List<User> listUsers = userService.listAllUsers();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping({"/register"})
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("person", new Person());
        model.addAttribute("pageTitle", "Add new user");
        return "signUp_form";
    }

    @PostMapping({"/process_register"})
    public String processRegistration(User user, Person person, RedirectAttributes ra) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setPerson(person);
        User registeredUser = userService.save(user);
        ra.addFlashAttribute("message", "The user has been saved successfully");
        return "redirect:/";
    }

    @PostMapping({"/process_Edit"})
    public String processEdit(User user, Person person, RedirectAttributes ra) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User update = userService.save(user);
        ra.addFlashAttribute("message", "The user has been saved successfully");
        return "redirect:/log";
    }

    @GetMapping({"/list_users/edit/{id}"})
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = userService.getById(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit model (ID: " + id + ")");
            return "edit_Form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/log";
        }
    }

    @GetMapping({"/list_users/delete/{id}"})
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            userService.deleteById(id);
            return "redirect:/";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }
}
