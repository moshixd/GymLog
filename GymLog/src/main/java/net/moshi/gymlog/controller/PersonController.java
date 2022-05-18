package net.moshi.gymlog.controller;

import net.moshi.gymlog.model.Person;
import net.moshi.gymlog.model.UserNotFoundException;
import net.moshi.gymlog.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping({"/list_user/edit/{id}"})
    public String showPersonForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Person person = personService.getById(id);
            model.addAttribute("person", person);
            model.addAttribute("pageTitle", "Edit model (ID: " + id + ")");
            return "edit_Person_Form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/log";
        }
    }

    @PostMapping({"/process_Person_Edit"})
    public String processEdit(Person person, RedirectAttributes ra) {
        Person update = personService.save(person);
        ra.addFlashAttribute("message", "The person has been saved successfully");
        return "redirect:/log";
    }
}
