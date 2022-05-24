package net.moshi.gymlog.service;

import net.moshi.gymlog.model.Exercise;
import net.moshi.gymlog.model.Person;
import net.moshi.gymlog.model.TrainingDay;
import net.moshi.gymlog.repository.TrainingDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingDayServiceImpl implements TrainingDayService {
    private final TrainingDayRepository trainingDayRepository;
    private final UserService userService;
    private final PersonService personService;
    private final ExerciseService exerciseService;

    @Autowired
    public TrainingDayServiceImpl(TrainingDayRepository trainingDayRepository, UserService userService, PersonService personService, ExerciseService exerciseService) {
        this.trainingDayRepository = trainingDayRepository;
        this.userService = userService;
        this.personService = personService;
        this.exerciseService = exerciseService;
    }

    @Override
    public TrainingDay getById(Integer id) {

        return trainingDayRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("error, not found"));
    }

    @Override
    public List<TrainingDay> listAllTrainingDays() {
        return trainingDayRepository.findAll();
    }

    @Override
    public TrainingDay save(TrainingDay trainingDay) {
        return trainingDayRepository.save(trainingDay);
    }

    @Override
    public void deleteTrainingdayById(Integer id) {
        boolean exist = trainingDayRepository.findById(id).isPresent();
        if (exist) {
            Person person = userService.getCurrentUser().getPerson();
            person.getTrainingDays().remove(trainingDayRepository.getById(id));
            trainingDayRepository.deleteById(id);
        } else throw new UsernameNotFoundException("Could not find any training days with ID." + id);
    }

    @Override
    public void addTrainingdayToPerson(TrainingDay trainingDay, Exercise exercise) {
        Person person = userService.getCurrentUser().getPerson();
        TrainingDay savedTrainingday = trainingDayRepository.save(trainingDay);
        Exercise savedExercice = exerciseService.save(exercise);
        person.getTrainingDays().add((savedTrainingday));
        savedTrainingday.getExercises().add(savedExercice);
        personService.save(person);
    }

    @Override
    public HashMap<Integer, List<Exercise>> getExercisesFromTrainingday() {
        Person person = userService.getCurrentUser().getPerson();
        List<TrainingDay> trainingDays = person.getTrainingDays();


        //Optional<TrainingDay> trainingday = Optional.ofNullable(trainingDayRepository.findById(id)
        //        .orElseThrow(() -> new EntityNotFoundException(id.toString())));

        //List<Exercise> getExercises = new ArrayList<>();

        HashMap<Integer, List<Exercise>> exerciseList = new HashMap<>();
        for (TrainingDay trainingDay : trainingDays) {
                exerciseList.put(trainingDay.getId(), new ArrayList<>());
            exerciseList.get(trainingDay.getId()).addAll(trainingDay.getExercises());
        }

        return exerciseList;
//    }
    }
}

