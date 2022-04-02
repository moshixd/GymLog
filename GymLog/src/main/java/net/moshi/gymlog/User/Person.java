package net.moshi.gymlog.User;

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

}