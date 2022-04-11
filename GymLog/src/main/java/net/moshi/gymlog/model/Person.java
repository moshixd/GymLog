package net.moshi.gymlog.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(schema = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false, length = 20)
    private float bodyWeight;

    @Column(nullable = false, length = 20)
    private float height;

    @OneToMany
    private Collection<Records> records;

}