package com.duth.engapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "MEANINGS")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Meaning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "DICTIONARYID", nullable = false)
    private Dictionary dictionaryid;

    @Column(name = "MEAN", nullable = false, length = 400)
    private String mean;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "TYPEID", nullable = false)
    private TypesOfWord typeid;
    public Meaning(Dictionary dictionary, String mean, TypesOfWord typesOfWord)
    {
        this.dictionaryid = dictionary;
        this.mean = mean;
        this.typeid = typesOfWord;
    }
}