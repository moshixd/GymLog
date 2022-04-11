package net.moshi.gymlog;

import net.moshi.gymlog.model.Records;
import net.moshi.gymlog.repository.PersonRepository;
import net.moshi.gymlog.repository.RecordsRepository;
import net.moshi.gymlog.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GymLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymLogApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RecordsRepository recorep, PersonService personService) {
        return args -> {
            recorep.save(new Records(1,"bench",100,200,300));

            personService.addRecords();

        };
    }
}