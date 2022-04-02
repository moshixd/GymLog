package net.moshi.gymlog.repository;

import net.moshi.gymlog.User.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
