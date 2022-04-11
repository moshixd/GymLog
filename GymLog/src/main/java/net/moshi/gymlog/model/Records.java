package net.moshi.gymlog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Records {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    //Benchpress personal record
    @Column(name = "benchpress_record")
    private float benchpressPr;

    //Squat personal record
    @Column(name = "squat_record")
    private float squatPr;

    //Deadlift personal record
    @Column(name = "deadlift_record")
    private float deadliftPr;
}
