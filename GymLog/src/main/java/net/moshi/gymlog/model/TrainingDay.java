package net.moshi.gymlog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "trainingday")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrainingDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Nullable
    private String workout;

    private Date date;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

}
