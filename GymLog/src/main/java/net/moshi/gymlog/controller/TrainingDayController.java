package net.moshi.gymlog.controller;

import net.moshi.gymlog.model.Exercise;
import net.moshi.gymlog.model.TrainingDay;
import net.moshi.gymlog.model.User;
import net.moshi.gymlog.model.UserNotFoundException;
import net.moshi.gymlog.service.ExerciseService;
import net.moshi.gymlog.service.TrainingDayService;
import net.moshi.gymlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TrainingDayController {

    @Autowired
    private UserService userService;

    @Autowired
    private TrainingDayService trainingDayService;

    @GetMapping({"/newTrainingDay"})
    public String showTrainingForm(Model model) {
        model.addAttribute("trainingday", new TrainingDay());
        model.addAttribute("pageTitle", "Add new trainingday");
        return "new_TrainingDay_Form";
    }

    @PostMapping({"/process_training"})
    public String processTraining(TrainingDay trainingDay, RedirectAttributes ra) {
        TrainingDay newTraining = trainingDayService.save(trainingDay);
        ra.addFlashAttribute("message", "The training has been saved successfully");
        return "redirect:/log";
    }

    @GetMapping({"/list_user_training/edit/{id}"})
    public String showTrainingDayForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            TrainingDay trainingDay = trainingDayService.getById(id);
            model.addAttribute("trainingday", trainingDay);
            model.addAttribute("pageTitle", "Edit model (ID: " + id + ")");
            return "edit_TrainingDay_Form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/log";
        }
    }

    @PostMapping({"/process_TrainingDay_Edit"})
    public String processEdit(TrainingDay trainingDay, RedirectAttributes ra) {
        TrainingDay update = trainingDayService.save(trainingDay);
        ra.addFlashAttribute("message", "The person has been saved successfully");
        return "redirect:/log";
    }
}
