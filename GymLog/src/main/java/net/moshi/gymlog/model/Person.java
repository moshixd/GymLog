package net.moshi.gymlog.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
