package net.moshi.gymlog.controller;

import net.moshi.gymlog.model.TrainingDay;
import net.moshi.gymlog.model.UserNotFoundException;
import net.moshi.gymlog.service.TrainingDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TrainingDayController {

    @Autowired
    private TrainingDayService trainingDayService;

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
