package com.duth.engapp.service;

import com.duth.engapp.entity.CustomUserDetails;
import com.duth.engapp.entity.User;
import com.duth.engapp.model.UserModel;
import com.duth.engapp.repository.ScoreRepository;
import com.duth.engapp.repository.SetsOfQuestionRepository;
import com.duth.engapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final int limit = 25;
    UserRepository userRepository;
    ScoreRepository scoreRepository;
    SetsOfQuestionRepository setsOfQuestionRepository;
    @Autowired
    public UserService(UserRepository userRepository, ScoreRepository scoreRepository, SetsOfQuestionRepository setsOfQuestionRepository) {
        this.userRepository = userRepository;
        this.scoreRepository = scoreRepository;
        this.setsOfQuestionRepository = setsOfQuestionRepository;
    }

    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findUserByEmail(email);

        if (user.getEmail().isBlank()) {
            throw new UsernameNotFoundException(email);
        }
        logger.info("loadUserByUsername " + user);
        return new CustomUserDetails(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );
        logger.info("loadUserById" + user.toString());
        return new CustomUserDetails(user);
    }
    public List<UserModel> getTopScore()
    {
        List<User> users = userRepository.findAll();
        List<UserModel> userModels = new java.util.ArrayList<>(users.stream().map(UserModel::new).toList());
        Collections.sort(userModels);
        return userModels;
    }
}