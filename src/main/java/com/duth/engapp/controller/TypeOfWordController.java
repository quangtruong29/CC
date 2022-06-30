package com.duth.engapp.controller;

import com.duth.engapp.entity.TypesOfWord;
import com.duth.engapp.repository.TypeOfWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/typeofword")
public class TypeOfWordController  extends RootController{
    TypeOfWordRepository typeOfWordRepository;
    @Autowired
    public TypeOfWordController(TypeOfWordRepository typeOfWordRepository)
    {
        this.typeOfWordRepository = typeOfWordRepository;
    }
    @GetMapping("/all")
    public List<TypesOfWord> getAll()
    {
        return typeOfWordRepository.findAll();
    }
}
