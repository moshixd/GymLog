package net.moshi.gymlog.service;

import net.moshi.gymlog.model.Exercise;
import net.moshi.gymlog.model.UserNotFoundException;

import java.util.List;

public interface ExerciseService {

    Exercise getById(Integer id) throws UserNotFoundException;

    List<Exercise> listAllExercises();

    Exercise save(Exercise exercise);

    void deleteExerciseById(Integer id);

    void addExerciseToTrainingday(Exercise exercise, Integer id);

}
