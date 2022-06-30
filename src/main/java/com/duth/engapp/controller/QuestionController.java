package com.duth.engapp.controller;

import com.duth.engapp.DTO.QuestionDTO;
import com.duth.engapp.DTO.QuestionDeleteDTO;
import com.duth.engapp.DTO.QuestionNotContainSetDTO;
import com.duth.engapp.dao.NewQuestion;
import com.duth.engapp.entity.Question;
import com.duth.engapp.entity.SetsOfQuestion;
import com.duth.engapp.entity.TypesOfQuestion;
import com.duth.engapp.payload.ApiResponse;
import com.duth.engapp.repository.QuestionRepository;
import com.duth.engapp.repository.SetsOfQuestionRepository;
import com.duth.engapp.repository.TypesOfQuestionRepository;
import com.duth.engapp.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/question")
public class QuestionController extends RootController {
    QuestionRepository questionRepository;
    TypesOfQuestionRepository typesOfQuestionRepository;
    SetsOfQuestionRepository  setsOfQuestionRepository;
    QuestionService questionService;
    Logger logger = LoggerFactory.getLogger(QuestionController.class);
    @Autowired
    public QuestionController(QuestionRepository questionRepository,
                              TypesOfQuestionRepository typesOfQuestionRepository,
                              QuestionService questionService,
                              SetsOfQuestionRepository  setsOfQuestionRepository) {
        this.questionRepository = questionRepository;
        this.typesOfQuestionRepository = typesOfQuestionRepository;
        this.questionService = questionService;
        this.setsOfQuestionRepository = setsOfQuestionRepository;
    }

    @GetMapping("/all")
    ApiResponse all() {
        ApiResponse response = new ApiResponse(200, "Get question success",null );
        List<Question> questions = questionRepository.findAll();
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for (Question q :questions)
        {
            questionDTOs.add(new QuestionDTO(q));
        }
        response.setResult(questionDTOs);
        logger.info(response.toString());
        return response;
    }
    @GetMapping("/get")
    ApiResponse getRead(@RequestParam(name = "type", required = false, defaultValue = "Reading") String type) {
        ApiResponse response = new ApiResponse(200, "Get question success",null );
        TypesOfQuestion typesOfQuestion = typesOfQuestionRepository.findTypesOfQuestionByName(type);
        if(typesOfQuestion == null)
        {
            response.setStatus(404);
            response.setMessage("Not found type");
            return response;
        }
        List<Question> questions = questionRepository.findQuestionByTypeid(typesOfQuestion);
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for (Question q :questions)
        {
            questionDTOs.add(new QuestionDTO(q));
        }
        response.setResult(questionDTOs);
        logger.info(response.toString());
        return response;
    }
    @GetMapping("/getbyset")
    ApiResponse getBySetId(@RequestParam(name = "set", required = false, defaultValue = "1") String set) {
        ApiResponse response = new ApiResponse(200, "Get question success",null );
        int setid = 1;
        try{
            setid = Integer.parseInt(set);
        }
        catch (Exception ex)
        {
            response.setStatus(404);
            response.setMessage("Fail type setid");
            return response;
        }
        SetsOfQuestion setsOfQuestion = setsOfQuestionRepository.findSetsOfQuestionById(setid);
        setsOfQuestionRepository.findAll().forEach(name -> {
            name.getScores().forEach(abc->{
                logger.info(abc.toString());
            });
        });
        if(setsOfQuestion == null)
        {
            response.setStatus(404);
            response.setMessage("Not found set");
            return response;
        }
        List<Question> questions = questionRepository.findAllBySetid(setsOfQuestion);
        List<QuestionNotContainSetDTO> questionDTOs = new ArrayList<>();
        for (Question q :questions)
        {
            questionDTOs.add(new QuestionNotContainSetDTO(q));
        }
        response.setResult(questions.stream().map(QuestionNotContainSetDTO::new).toList());
        logger.info(response.toString());
        return response;
    }
    @PostMapping("/update")
    ApiResponse save(@Valid @RequestBody NewQuestion question) {
        ApiResponse response = new ApiResponse(200, "Insert success",null );
        Question question1 = question.toQuestion();
        if(question.getId() != null)
        {
            Optional<Question>  question2  = questionRepository.findById(question.getId());
            if(question2.isPresent())
            {
                response.setMessage("Update success");
            }
        }
        try{
            question1 = questionRepository.save(question1);
            TypesOfQuestion typesOfQuestion = typesOfQuestionRepository.getById(question1.getTypeid().getId());
            SetsOfQuestion setsOfQuestion = setsOfQuestionRepository.getById(question1.getSetid().getId());
            question1.setTypeid(typesOfQuestion);
            question1.setSetid(setsOfQuestion);
            response.setResult(new QuestionNotContainSetDTO(question1));
        }catch (Exception ex)
        {
            response.setStatus(500);
            response.setMessage("Update failed");
            ex.printStackTrace();
        }
        return response;
    }

    @GetMapping("/random")
    ApiResponse random() {
        ApiResponse response = new ApiResponse(200, "Get random question success",null );
        List<Question> questions = questionService.getRandom();
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for (Question q :questions)
        {
            questionDTOs.add(new QuestionDTO(q));
        }
        response.setResult(questionDTOs);
        logger.info(response.toString());
        return response;
    }
    @DeleteMapping("/")
    ApiResponse delete(@Valid @RequestBody QuestionDeleteDTO questionDeleteDTO) {
        ApiResponse response = new ApiResponse(200, "DELETE SUCCESS",null );
        Optional<Question> questions = questionRepository.findById(questionDeleteDTO.getId());
        if(questions.isEmpty())
        {
            response.setStatus(404);
            response.setMessage("Not found Question");
            return response;
        }
        try
        {
            questionRepository.deleteById(questionDeleteDTO.getId());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            response.setStatus(500);
            response.setMessage("Server error");
            return response;
        }
        Question question1 = questions.get();
        TypesOfQuestion typesOfQuestion = typesOfQuestionRepository.getById(question1.getTypeid().getId());
        SetsOfQuestion setsOfQuestion = setsOfQuestionRepository.getById(question1.getSetid().getId());
        question1.setTypeid(typesOfQuestion);
        question1.setSetid(setsOfQuestion);
        response.setResult(new QuestionNotContainSetDTO(question1));
        logger.info(response.toString());
        return response;
    }
}
