package net.moshi.gymlog.controller;

import net.moshi.gymlog.model.TrainingDay;
import net.moshi.gymlog.service.TrainingDayService;
import net.moshi.gymlog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {


    private final UserService userService;
    private final TrainingDayService trainingDayService;


    public TestController(UserService userService, TrainingDayService trainingDayService) {
        this.userService = userService;
        this.trainingDayService = trainingDayService;
    }

    @PostMapping(path = "/save")
    private ResponseEntity<TrainingDay> newMember() {
        System.out.println("THIS ONE IS SAVED");
        return new ResponseEntity<>(trainingDayService.save(new TrainingDay()),HttpStatus.OK);
    }

    @GetMapping("/getall")
    private ResponseEntity<List<TrainingDay>> getPersons() {
        return new ResponseEntity<>(userService.getCurrentUser().getPerson().getTrainingDays(), HttpStatus.OK);
    }
}