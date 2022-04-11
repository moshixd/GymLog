package net.moshi.gymlog.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(schema = "person")
public class Person {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false, length = 20)
    private float bodyWeight;

    @Column(nullable = false, length = 20)
    private float height;

    //Benchpress personal record
    @Column
    private float bpPr;

    //Squat personal record
    @Column
    private float sqPr;

    //Deadlift personal record
    @Column
    private float dlPr;

}