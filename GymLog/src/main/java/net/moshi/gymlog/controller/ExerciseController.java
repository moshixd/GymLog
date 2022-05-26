package net.moshi.gymlog.controller;

import net.moshi.gymlog.model.Exercise;
import net.moshi.gymlog.model.UserNotFoundException;
import net.moshi.gymlog.service.ExerciseService;
import net.moshi.gymlog.service.TrainingDayService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final TrainingDayService trainingDayService;

    public ExerciseController(ExerciseService exerciseService, TrainingDayService trainingDayService) {
        this.exerciseService = exerciseService;
        this.trainingDayService = trainingDayService;
    }

    @GetMapping({"/list_user_exercise/edit/{id}"})
    public String editExerciseForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) throws UserNotFoundException {
        Exercise exercise = exerciseService.getById(id);
        model.addAttribute("exercise", exercise);
        model.addAttribute("pageTitle", "Edit model (ID: " + id + ")");
        return "edit_Exercise_Form";
    }

    @GetMapping({"/newExercise"})
    public String addExercise(Model model) {
        model.addAttribute("exercise", new Exercise());
        model.addAttribute("pageTitle", "Add new Exercise");
        return "new_Exercise_Form";
    }

    @PostMapping({"/process_exercise"})
    public String processExercise(Exercise exercise, RedirectAttributes ra) throws UserNotFoundException {
        Exercise savedExercise = exerciseService.save(exercise);
        ra.addFlashAttribute("message", "The exercise has been saved successfully");
        return "redirect:/log";
    }

    @PostMapping({"/process_exercise_edit/{id}"})
    public String processExerciseEdit(@PathVariable("id") Integer id, Exercise exercise, RedirectAttributes ra) {
        exerciseService.save(exercise);
        ra.addFlashAttribute("message", "The exercise has been edited successfully");
        return "redirect:/log";
    }

    @GetMapping({"/list_user_exercise/delete/{id}"})
    public String deleteExercise(@PathVariable("id") Integer id) {
        exerciseService.deleteExerciseById(id);
        return "redirect:/log";
    }
}
