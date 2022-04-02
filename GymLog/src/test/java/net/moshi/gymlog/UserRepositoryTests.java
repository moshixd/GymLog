package net.moshi.gymlog;

import static org.assertj.core.api.Assertions.assertThat;

import net.moshi.gymlog.User.User;
import net.moshi.gymlog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("moshixD@gmail.com");
        user.setPassword("moshi2020");
        user.setFirstName("Moshi");
        user.setLastName("Hoshi");

        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getId().equals(user.getId()));
    }

    @Test
    public void testFindUserByEmail() {
        String email = "111@lol.se";

        User user = repo.findByEmail(email);

        assertThat(user).isNotNull();
    }

    @Test
    public void testDeleteUserById() {
        Integer userId = 47;
        repo.deleteById(userId);
    }
}
