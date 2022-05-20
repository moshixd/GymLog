package net.moshi.gymlog.service;

import net.moshi.gymlog.model.Exercise;
import net.moshi.gymlog.model.UserNotFoundException;
import net.moshi.gymlog.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {
    private final ExerciseRepository repo;


    public ExerciseService(ExerciseRepository repo) {
        this.repo = repo;
    }

    public Exercise getById(Integer id) throws UserNotFoundException {
        Optional<Exercise> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new UserNotFoundException("Could not find any Person with ID." + id);
        }
    }

    public List<Exercise> listAllExercises() {
        return repo.findAll();
    }

    public Exercise save(Exercise exercise) {
        return repo.save(exercise);
    }
}
