package net.moshi.gymlog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name = "persons")
@Getter
@Setter
public class Person {

    @Column
    private String body_weight;

    @Column
    private float height;
}
