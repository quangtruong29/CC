package com.duth.engapp.repository;

import com.duth.engapp.entity.TypesOfQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypesOfQuestionRepository extends JpaRepository<TypesOfQuestion, Integer> {
    TypesOfQuestion findTypesOfQuestionByName(String name);
}