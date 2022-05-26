package net.moshi.gymlog.service;

import net.moshi.gymlog.model.Exercise;
import net.moshi.gymlog.model.UserNotFoundException;
import net.moshi.gymlog.repository.ExerciseRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository repo;
    private final UserService userService;
    private final TrainingDayService trainingDayService;

    public ExerciseServiceImpl(ExerciseRepository repo, UserService userService, TrainingDayService trainingDayService) {
        this.repo = repo;
        this.userService = userService;
        this.trainingDayService = trainingDayService;
    }

    @Override
    public Exercise getById(Integer id) throws UserNotFoundException {
        Optional<Exercise> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new UserNotFoundException("Could not find any Exercise with ID." + id);
        }
    }

    @Override
    public List<Exercise> listAllExercises() {
        return repo.findAll();
    }

    @Override
    public Exercise save(Exercise exercise) {
        return repo.save(exercise);
    }

    @Override
    public void deleteExerciseById(Integer id) {
        boolean exist = repo.findById(id).isPresent();
        if (exist) {
            repo.deleteById(id);
        } else throw new UsernameNotFoundException("Could not find any training days with ID." + id);
    }

    @Override
    public void addExerciseToTrainingday(Exercise exercise) {

        Exercise savedExercise = repo.save(exercise);

    }
}

