package com.duth.engapp.repository;

import com.duth.engapp.entity.Score;
import com.duth.engapp.entity.SetsOfQuestion;
import com.duth.engapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findAllBySetidAndUserid(SetsOfQuestion setsOfQuestion, User user);

    List<Score> findAllBySetid(SetsOfQuestion setsOfQuestion);
}