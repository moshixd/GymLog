package net.moshi.gymlog.service;

import net.moshi.gymlog.model.Person;
import net.moshi.gymlog.model.TrainingDay;
import net.moshi.gymlog.model.User;
import net.moshi.gymlog.model.UserNotFoundException;
import net.moshi.gymlog.repository.TrainingDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingDayServiceImpl implements TrainingDayService {
    private final TrainingDayRepository trainingDayRepository;
    private final UserService userService;
    private final PersonService personService;

    @Autowired
    public TrainingDayServiceImpl(TrainingDayRepository trainingDayRepository, UserService userService, PersonService personService) {
        this.trainingDayRepository = trainingDayRepository;
        this.userService = userService;
        this.personService = personService;
    }

    @Override
    public TrainingDay getById(Integer id) {

        return trainingDayRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("error, not found"));
    }

    @Override
    public List<TrainingDay> listAllTrainingDays() {
        return trainingDayRepository.findAll();
    }

//    @Override
//    public TrainingDay save(TrainingDay trainingDay) {
//        User user = userService.getCurrentUser();
//        Person person = user.getPerson();
//        List<TrainingDay> trainingDays = person.getTrainingDays();
//        person.setTrainingDays(trainingDays);
//        person.getTrainingDays().add(trainingDay);
//        TrainingDay savedTrainingday = trainingDayRepository.save(trainingDay);
//        person.getTrainingDays().add(savedTrainingday);
//        Person savedPerson = personService.save(person);
//        return savedTrainingday;
//    }

    @Override
    public TrainingDay save(TrainingDay trainingDay) {
        return  trainingDayRepository.save(trainingDay);
    }

    @Override
    public void deleteTrainingdayById(Integer id) {
        boolean exist = trainingDayRepository.findById(id).isPresent();
        if (exist) {
            trainingDayRepository.deleteById(id);
        } else throw new UsernameNotFoundException("Could not find any users with ID." + id);
    }

//    @Override
//    public void addTrainingdayToPerson(TrainingDay trainingDay) throws UserNotFoundException {
//        trainingDay.setWorkout(trainingDay.getWorkout());
//        Person person = userService.getCurrentUser().getPerson();
//        //trainingDay.setPerson(person);
//        TrainingDay savedDay = trainingDayRepository.save(trainingDay);
//        person.getTrainingDays().add(savedDay);
//        personService.save(person);
//    }
    @Override
    public void addTrainingdayToPerson(TrainingDay trainingDay) {
        Person person = userService.getCurrentUser().getPerson();
        TrainingDay savedTrainingday = trainingDayRepository.save(trainingDay);
        person.getTrainingDays().add((savedTrainingday));
        personService.save(person);
    }
}


//    public TrainingDay updateTrainingday(TrainingDay trainingDay) {
//        TrainingDay trainingDayDB
//        User updatedUser = getCurrentUser();
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        updatedUser.setFirstName(user.getFirstName());
//        updatedUser.setLastName(user.getLastName());
//        updatedUser.setPassword(encoder.encode(user.getPassword()));
//        return repo.save(updatedUser);
//    }

