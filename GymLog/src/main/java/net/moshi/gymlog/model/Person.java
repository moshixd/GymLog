package net.moshi.gymlog.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Nullable
    private float bodyWeight;
    @Nullable
    private float height;
    @Nullable
    private float squat;
    @Nullable
    private float benchpress;
    @Nullable
    private float deadlift;

    @OneToOne(mappedBy = "person")
    private User user;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "person_id")
    private List<TrainingDay> trainingDays = new ArrayList<>();

}
