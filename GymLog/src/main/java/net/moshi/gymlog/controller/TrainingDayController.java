package net.moshi.gymlog.controller;

import net.moshi.gymlog.model.Exercise;
import net.moshi.gymlog.model.TrainingDay;
import net.moshi.gymlog.model.UserNotFoundException;
import net.moshi.gymlog.service.TrainingDayService;
import net.moshi.gymlog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TrainingDayController {


    private final TrainingDayService trainingDayService;
    private final UserService userService;

    public TrainingDayController(TrainingDayService trainingDayService, UserService userService) {
        this.trainingDayService = trainingDayService;
        this.userService = userService;
    }


    @GetMapping({"/list_user_training/edit/{id}"})
    public String showTrainingDayForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        TrainingDay trainingDay = trainingDayService.getById(id);
        model.addAttribute("trainingday", trainingDay);
        model.addAttribute("pageTitle", "Edit model (ID: " + id + ")");
        return "edit_TrainingDay_Form";
    }

    @GetMapping({"/newTrainingDay"})
    public String showTrainingForm(Model model) {
        model.addAttribute("trainingday", new TrainingDay());
        model.addAttribute("exercise", new Exercise());
        model.addAttribute("pageTitle", "Add new trainingday");
        return "new_TrainingDay_Form";
    }

    @PostMapping({"/process_training"})
    public String processTraining(TrainingDay trainingDay, Exercise exercise, RedirectAttributes ra) throws UserNotFoundException {
        trainingDayService.addTrainingdayToPerson(trainingDay, exercise);
        ra.addFlashAttribute("message", "The training has been saved successfully");
        return "redirect:/log";
    }

    @PostMapping({"/process_TrainingDay_Edit"})
    public String processTrainingdayEdit(TrainingDay trainingDay, RedirectAttributes ra) {
        trainingDayService.save(trainingDay);
        ra.addFlashAttribute("message", "The person has been saved successfully");
        return "redirect:/log";
    }

    @GetMapping({"/list_user_training/delete/{id}"})
    public String deleteUser(@PathVariable("id") Integer id) {
        trainingDayService.deleteTrainingdayById(id);
        return "redirect:/log";
    }
}
