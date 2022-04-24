package net.moshi.gymlog.repository;

import net.moshi.gymlog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT  u from User u where u.email = ?1")
    User findByEmail(String email);

}