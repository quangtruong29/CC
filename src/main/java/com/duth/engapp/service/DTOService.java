package com.duth.engapp.service;

import com.duth.engapp.DTO.SetsOfQuestionUserDTO;
import com.duth.engapp.entity.SetsOfQuestion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DTOService {
    public List<SetsOfQuestionUserDTO> convertToDTO(List<SetsOfQuestion> setsOfQuestions, Long id)
    {
        setsOfQuestions.forEach(n->{
            n.setScores(n.getScores().stream().filter(m-> Objects.equals(m.getUserid().getId(), id)).collect(Collectors.toUnmodifiableSet()));
        });
        return setsOfQuestions.stream().map(SetsOfQuestionUserDTO::new).toList();
    }
}
