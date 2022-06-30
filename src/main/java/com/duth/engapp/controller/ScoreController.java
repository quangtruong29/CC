package com.duth.engapp.controller;

import com.duth.engapp.DTO.ScoreDTO;
import com.duth.engapp.dao.GetScore;
import com.duth.engapp.entity.CustomUserDetails;
import com.duth.engapp.entity.Score;
import com.duth.engapp.entity.SetsOfQuestion;
import com.duth.engapp.entity.User;
import com.duth.engapp.payload.ApiResponse;
import com.duth.engapp.repository.ScoreRepository;
import com.duth.engapp.repository.SetsOfQuestionRepository;
import com.duth.engapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/score")
public class ScoreController extends RootController {
    ScoreRepository scoreRepository;
    UserRepository userRepository;
    SetsOfQuestionRepository setsOfQuestionRepository;
    Logger logger = LoggerFactory.getLogger(ScoreController.class);
    @Autowired
    public ScoreController(ScoreRepository scoreRepository, UserRepository userRepository, SetsOfQuestionRepository setsOfQuestionRepository) {
        this.scoreRepository = scoreRepository;
        this.userRepository = userRepository;
        this.setsOfQuestionRepository = setsOfQuestionRepository;
    }
    @GetMapping("/")
    List<Score> getByName() {
        return scoreRepository.findAll();
    }
    @PostMapping("/new")
    ApiResponse newScore(@Valid @RequestBody ScoreDTO scoreDTO) {
        ApiResponse response = new ApiResponse(200, "Creat new score success",null );
        Score score = new Score();
        CustomUserDetails userDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByEmail(userDetails.getUser().getEmail());
        Optional<SetsOfQuestion> setsOfQuestion = setsOfQuestionRepository.findById(Integer.valueOf(scoreDTO.getSetid()));
        if(setsOfQuestion.isEmpty())
        {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("Not found set of question");
            return  response;
        }
        score.setUserid(user);
        score.setSetid(setsOfQuestion.get());
        score.setScore(Double.valueOf(scoreDTO.getScore()));
        try {
            score = scoreRepository.save(score);
            response.setResult(new ScoreDTO(score));
            logger.info(score.toString());
        }catch (Exception ex
        ){
            ex.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("SERVER ERROR");
        }
        return response;
    }
    @GetMapping("/get")
    ApiResponse newScore(@Valid @RequestBody GetScore getScore) {
        ApiResponse response = new ApiResponse(200, "Get score success",null );
        List<Score> score;
        CustomUserDetails userDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByEmail(userDetails.getUser().getEmail());
        Optional<SetsOfQuestion> setsOfQuestion = setsOfQuestionRepository.findById(Integer.valueOf(getScore.getId()));
        if(setsOfQuestion.isEmpty())
        {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("Not found set of question");
            return  response;
        }
        try {
            score = scoreRepository.findAllBySetidAndUserid(setsOfQuestion.get(), user);
            List<ScoreDTO> scoreDTOS = new ArrayList<>();
            score.forEach(n->{scoreDTOS.add(new ScoreDTO(n));});
            response.setResult(scoreDTOS);
        }catch (Exception ex){
            ex.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("SERVER ERROR");
        }
        return response;
    }
}
