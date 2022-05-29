package net.moshi.gymlog;

import net.moshi.gymlog.model.Person;
import net.moshi.gymlog.model.User;
import net.moshi.gymlog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    String email = "moshixD@gmail.com";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    @Rollback
    public void testCreateUserAndPerson() {
        User user = new User();
        Person person = new Person();
        user.setEmail("moshixD@gmail.com");
        user.setPassword("moshi2020");
        user.setFirstName("Moshi");
        user.setLastName("Hoshi");
        user.setPerson(person);

        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getId().equals(user.getId()));

    }

    @Test
    public void testFindUserByEmail() {

        User user = repo.findByEmail(email);

        assertThat(user).isNotNull();
    }

    @Test
    @Rollback
    public void testEditUser() {
        User user = repo.findByEmail(email);
        user.setFirstName("Hoshi");
        user.setLastName("Koshi");

        assertThat(user.getFirstName()).isEqualTo("Hoshi");
        assertThat(user.getLastName()).isEqualTo("Koshi");

    }

    @Rollback
    @Test
    public void testDeleteUserById() {
        Integer userId = 1;
        repo.deleteById(userId);
    }
}
