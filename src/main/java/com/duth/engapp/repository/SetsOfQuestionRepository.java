package com.duth.engapp.repository;

import com.duth.engapp.entity.SetsOfQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetsOfQuestionRepository extends JpaRepository<SetsOfQuestion, Integer> {
    SetsOfQuestion findSetsOfQuestionById(Integer id);
    SetsOfQuestion findFirstByOrderByIdDesc();

    SetsOfQuestion deleteAllById(Integer id);
}