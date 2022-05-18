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
    private float exercise;
    @Nullable
    private float weight;
    @Nullable
    private float reps;
    @Nullable
    private float sets;
}
