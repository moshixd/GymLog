package net.moshi.gymlog.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public UserController() {
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
        model.addAttribute("pageTitle", "Add new user");
        return "signUp_form";
    }

    @PostMapping({"/process_register"})
    public String processRegistration(User user, RedirectAttributes ra) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.save(user);
        ra.addFlashAttribute("message", "The user has been saved successfully");
        return "redirect:/list_users";
    }

    @GetMapping({"/list_users/edit/{id}"})
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = userService.getById(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return "signUp_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/list_users";
        }
    }

    @GetMapping({"/list_users/delete/{id}"})
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) throws UserNotFoundException {
        try {
            userService.deleteById(id);
            return "redirect:/list_users";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/list_users";
        }
    }
}

