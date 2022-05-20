package net.moshi.gymlog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
