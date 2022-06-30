package com.duth.engapp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "QUESTIONS")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@ToString
public class Question implements Serializable {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "QUESTCONTENT", nullable = false, length = 50)
    private String questcontent;

    @Column(name = "A", nullable = false, length = 50)
    private String a;

    @Column(name = "B", nullable = false, length = 50)
    private String b;

    @Column(name = "C", nullable = false, length = 50)
    private String c;

    @Column(name = "D", nullable = false, length = 50)
    private String d;

    @Column(name = "ANSWER", nullable = false, length = 50)
    private String answer;

    @Column(name = "IMAGE", nullable = false, length = 100)
    private String image;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "SETID", nullable = false)
    private SetsOfQuestion setid;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "TYPEID", nullable = false)
    private TypesOfQuestion typeid;

    public Question(Long id, String questcontent, String a, String b, String c, String d, String answer, String image, TypesOfQuestion typeid, SetsOfQuestion setid) {
        this.id  = id;
        this.questcontent = questcontent;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
        this.image = image;
        this.typeid = typeid;
        this.setid = setid;
    }
}