package com.duth.engapp.service;

import com.duth.engapp.repository.SetsOfQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetOfQuestionService {
    SetsOfQuestionRepository setsOfQuestionRepository;

    @Autowired
    public SetOfQuestionService(SetsOfQuestionRepository setsOfQuestionRepository) {
        this.setsOfQuestionRepository = setsOfQuestionRepository;
    }
}
