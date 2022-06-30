package com.duth.engapp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TYPESOFWORD")
@Getter
@Setter
@ToString
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class TypesOfWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "SHORTCUT", nullable = false, length = 50)
    private String shortcut;

    @Column(name = "NAME", length = 50)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "typeid")
    @ToString.Exclude
    private Set<Meaning> meanings = new LinkedHashSet<>();

    public TypesOfWord() {

    }
    public TypesOfWord(Integer id, String shortcut, String name) {
        this.id = id;
        this.shortcut = shortcut;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TypesOfWord that = (TypesOfWord) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}