package com.duth.engapp.repository;

import com.duth.engapp.entity.Question;
import com.duth.engapp.entity.SetsOfQuestion;
import com.duth.engapp.entity.TypesOfQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findAll(Pageable pageable);

    List<Question> findQuestionByTypeid(TypesOfQuestion typeid);

    List<Question> findAllBySetid(SetsOfQuestion setid);

    void deleteAllBySetid(SetsOfQuestion setid);

}