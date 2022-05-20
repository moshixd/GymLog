package net.moshi.gymlog;

import static org.assertj.core.api.Assertions.assertThat;

import net.moshi.gymlog.model.Person;
import net.moshi.gymlog.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class PersonRepositoryTest {

    String email = "moshixD@gmail.com";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository repo;

    @Test
    public void testEditPerson() {
        Person person = repo.getById(3);
        person.setHeight(160);

        assertThat(person.getHeight()).isEqualTo(160);
    }
}