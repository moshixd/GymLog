package net.moshi.gymlog.controller;

import net.moshi.gymlog.model.*;
import net.moshi.gymlog.service.ExerciseService;
import net.moshi.gymlog.service.TrainingDayService;
import net.moshi.gymlog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService;
    private final ExerciseService exerciseService;
    private final TrainingDayService trainingDayService;

    public UserController(UserService userService, ExerciseService exerciseService, TrainingDayService trainingDayService) {
        this.userService = userService;
        this.exerciseService = exerciseService;
        this.trainingDayService = trainingDayService;
    }


    @GetMapping({"/log"})
    public String loginUser() {
        return "redirect:/list_user/" + userService.getCurrentUser().getEmail();
    }

    @GetMapping({"list_user/{email}"})
    public String viewUser(@PathVariable("email") String email, Model model) throws UserNotFoundException {
        model.addAttribute("foundUser", userService.getByEmail(email));
        model.addAttribute("foundperson", userService.getByEmail(email).getPerson());
        model.addAttribute("foundtraining", userService.getByEmail(email).getPerson().getTrainingDays());


        HashMap<Integer, List<Exercise>> exerciseList = trainingDayService.getExercisesFromTrainingday();


        for(Map.Entry<Integer, List<Exercise>> entry : exerciseList.entrySet()) {
            List<Exercise> listExercise = entry.getValue();
            Integer trainingdayId = entry.getKey();

            model.addAttribute("exercise", listExercise);
//            TrainingDay trainingDayDB = trainingDayService.getById(trainingdayId);
//            trainingDayDB.getExercises().add()trainingDayService.(activityID).getVariousListItem());
//            activityService.addItemToVariousList(entry.getKey(), activityDB);
//
//            model.addAttribute("activity", activityService.getActivityById(entry.getKey()));



        }
        //model.addAttribute("foundexercises", userService.getByEmail(email).getPerson().getTrainingDays().stream().map(TrainingDay::getExercises).collect(Collectors.toList()));
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
        userService.createUserandPerson(user, person);
        ra.addFlashAttribute("message", "The user has been saved successfully");
        return "redirect:/";
    }

    @PostMapping({"/process_edit"})
    public String processEdit(User user, RedirectAttributes ra) {
        userService.updateUser(user);
        ra.addFlashAttribute("messages", "The user has been saved successfully");
        return "redirect:/log";
    }

    @GetMapping({"/list_user/edit"})
    public String showUserEditForm(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "edit_Form";
    }

    @GetMapping({"/list_users/edit/{id}"})
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Edit model (ID: " + id + ")");
        return "edit_Form";
    }

    @GetMapping({"/list_users/delete/{id}"})
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return "redirect:/list_users";
    }
}
