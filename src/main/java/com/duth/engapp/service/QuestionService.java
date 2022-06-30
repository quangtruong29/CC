package com.duth.engapp.service;

import com.duth.engapp.entity.Question;
import com.duth.engapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class QuestionService {
    QuestionRepository questionRepository;
    @Autowired
    public QuestionService(QuestionRepository questionRepository)
    {
        this.questionRepository = questionRepository;
    }

    public List<Question> getRandom()
    {
        List<Question> questions = questionRepository.findAll();
        Collections.shuffle(questions);
        int size = questions.size();
        return questions.subList(0, Math.min(size, 10));
    }
}
