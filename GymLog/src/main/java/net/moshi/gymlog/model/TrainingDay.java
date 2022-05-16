package net.moshi.gymlog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "trainingday")
@NoArgsConstructor
@Transactional
@Getter
@Setter
public class TrainingDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private Date date;
}
