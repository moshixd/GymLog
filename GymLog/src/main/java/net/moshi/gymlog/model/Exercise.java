package net.moshi.gymlog.model;

import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Table(name = "exercise")
@NoArgsConstructor
@AllArgsConstructor
@Transactional
@Getter
@Setter
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Nullable
    private String exerciseName;
    @Nullable
    private float weight;
    @Nullable
    private int reps;
    @Nullable
    private int sets;

//    @ManyToOne
//    @JoinColumn(name = "training_id", nullable = false)
//    private TrainingDay trainingDay;

}
