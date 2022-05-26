package net.moshi.gymlog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exercise)) return false;
        Exercise exercise = (Exercise) o;
        return Float.compare(exercise.getWeight(), getWeight()) == 0 && getReps() == exercise.getReps() && getSets() == exercise.getSets() && getId().equals(exercise.getId()) && Objects.equals(getExerciseName(), exercise.getExerciseName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getExerciseName(), getWeight(), getReps(), getSets());
    }
}
