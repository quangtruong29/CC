package com.duth.engapp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Users")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;

    @JsonIgnore
    @Column(name = "PASSWORD", nullable = false, length = 50)
    private String password;

    @Column(name = "FIRSTNAME", length = 50)
    private String firstname;

    @Column(name = "LASTNAME", length = 50)
    private String lastname;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "AVATAR", length = 100)
    private String avatar;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ROLEID", nullable = false)
    private Role roleid;

    @OneToMany(mappedBy = "userid")
    @ToString.Exclude
    private Set<Score> scores = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "Favorites",
            joinColumns = @JoinColumn(name = "USERID"),
            inverseJoinColumns = @JoinColumn(name = "DICTIONARYID"))
    @ToString.Exclude
    private Set<Dictionary> dictionaries = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}