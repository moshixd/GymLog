package net.moshi.gymlog.service;

import net.moshi.gymlog.model.TrainingDay;
import net.moshi.gymlog.model.UserNotFoundException;

import java.util.List;


public interface TrainingDayService {

    TrainingDay getById(Integer id);

    List<TrainingDay> listAllTrainingDays();

    TrainingDay save(TrainingDay trainingDay);

    void deleteTrainingdayById(Integer id);

    void addTrainingdayToPerson(TrainingDay trainingDay) throws UserNotFoundException;

    TrainingDay showExercise(TrainingDay trainingDay);
}
