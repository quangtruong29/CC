package com.duth.engapp.controller;

import com.duth.engapp.DTO.*;
import com.duth.engapp.entity.CustomUserDetails;
import com.duth.engapp.entity.Score;
import com.duth.engapp.entity.SetsOfQuestion;
import com.duth.engapp.payload.ApiResponse;
import com.duth.engapp.repository.QuestionRepository;
import com.duth.engapp.repository.ScoreRepository;
import com.duth.engapp.repository.SetsOfQuestionRepository;
import com.duth.engapp.service.DTOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/setofquestion")
public class SetOfQuestionController extends RootController {
    SetsOfQuestionRepository setsOfQuestionRepository;
    QuestionRepository questionRepository;
    ScoreRepository scoreRepository;
    DTOService dtoService;
    Logger logger = LoggerFactory.getLogger(SetOfQuestionController.class);

    @Autowired
    public SetOfQuestionController(SetsOfQuestionRepository setsOfQuestionRepository,
                                   DTOService dtoService,
                                   QuestionRepository questionRepository,
                                   ScoreRepository scoreRepository) {
        this.setsOfQuestionRepository = setsOfQuestionRepository;
        this.dtoService = dtoService;
        this.scoreRepository = scoreRepository;
        this.questionRepository = questionRepository;
    }
    @GetMapping("/all")
    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse(200, "GET success", null, null);
        List<SetsOfQuestion> setsOfQuestions = setsOfQuestionRepository.findAll();
        List<SetsOfQuestionDTO> setsOfQuestionDTOS = setsOfQuestions
                .stream()
//                .filter(n-> n.getQuestions() != null && n.getQuestions().size() > 0)
                .map(SetsOfQuestionDTO::new).toList();
        response.setResult(setsOfQuestionDTOS);
        return response;
    }
    @GetMapping("/get")
    public ApiResponse get() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApiResponse response = new ApiResponse(200, "GET success", null, null);
        List<SetsOfQuestion> setsOfQuestions = setsOfQuestionRepository.findAll();
        List<SetsOfQuestionUserDTO> setsOfQuestionUserDTOS = dtoService.convertToDTO(setsOfQuestions
                .stream()
                .filter(n-> n.getQuestions() != null && n.getQuestions().size() > 0)
                .toList(), userDetails.getUser().getId());
        response.setResult(setsOfQuestionUserDTOS);
        return response;
    }
    @PostMapping("/new")
    public ApiResponse create(@Valid @RequestBody SetOfQuestionUpdateDTO setsOfQuestionDTO) {
        SetsOfQuestion setsOfQuestion = setsOfQuestionDTO.setsOfQuestion();
        SetsOfQuestion topValue = setsOfQuestionRepository.findFirstByOrderByIdDesc();
        setsOfQuestion.setId(topValue.getId() + 1);

        ApiResponse response = new ApiResponse(200, "Create success", null, null);
        try
        {
            logger.info(setsOfQuestion.toString());
            setsOfQuestion = setsOfQuestionRepository.save(setsOfQuestion);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            response.setStatus(500);
            response.setMessage("Server error");
            return response;
        }
        response.setResult(new SetsOfQuestionDTO(setsOfQuestion));
        return response;
    }
    @PostMapping("/update")
    public ApiResponse update(@Valid @RequestBody SetsOfQuestionCreateDTO setsOfQuestionDTO) {
        SetsOfQuestion setsOfQuestion = setsOfQuestionDTO.setsOfQuestion();
        ApiResponse response = new ApiResponse(200, "Update success", null, null);
        try
        {
            setsOfQuestion = setsOfQuestionRepository.save(setsOfQuestion);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            response.setStatus(500);
            response.setMessage("Server error");
            return response;
        }
        response.setResult(new SetsOfQuestionDTO(setsOfQuestion));
        return response;
    }
    @DeleteMapping("/")
    @Transactional
    public ApiResponse delete(@Valid @RequestBody SetOfQuestionDeleteDTO setsOfQuestionDTO) {
        Optional<SetsOfQuestion> setsOfQuestion = setsOfQuestionRepository.findById(setsOfQuestionDTO.getId());
        ApiResponse response = new ApiResponse(200, "DELETE success", null, null);
        if(setsOfQuestion.isEmpty())
        {
            response.setStatus(404);
            response.setMessage("Not found Set of question");
            return response;
        }
        List<Score> scores = scoreRepository.findAllBySetid(setsOfQuestion.get());
        if(scores != null && scores.size() > 0)
        {
            response.setStatus(200);
            response.setMessage("Set have scores");
            return response;
        }
        try
        {
            questionRepository.deleteAllBySetid(setsOfQuestion.get());
            setsOfQuestionRepository.delete(setsOfQuestion.get());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            response.setStatus(500);
            response.setMessage("Server error");
            return response;
        }
        response.setResult(new SetsOfQuestionDTO(setsOfQuestion.get()));
        return response;
    }
}
