package net.moshi.gymlog.service;

import net.moshi.gymlog.model.TrainingDay;
import net.moshi.gymlog.model.UserNotFoundException;
import net.moshi.gymlog.repository.TrainingDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingDayService {
    private final TrainingDayRepository repo;

    @Autowired
    public TrainingDayService(TrainingDayRepository repo) {
        this.repo = repo;
    }

    public TrainingDay getById(Integer id) throws UserNotFoundException {
        Optional<TrainingDay> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new UserNotFoundException("Could not find any Person with ID." + id);
        }
    }

    public List<TrainingDay> listAllTrainingDays() {
        return repo.findAll();
    }

    public TrainingDay save(TrainingDay trainingDay) {
        return repo.save(trainingDay);
    }

}
