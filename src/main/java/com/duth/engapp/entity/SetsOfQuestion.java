package com.duth.engapp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SETSOFQUESTIONS")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@ToString
public class SetsOfQuestion {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "setid")
    @ToString.Exclude
    private Set<Question> questions = new LinkedHashSet<>();

//    @JsonIgnore
    @OneToMany(mappedBy = "setid")
    @ToString.Exclude
    private Set<Score> scores = new LinkedHashSet<>();
}