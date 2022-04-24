package net.moshi.gymlog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Transactional
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Person person;
}
