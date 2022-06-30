package com.duth.engapp.controller;

import com.duth.engapp.DTO.DictionaryDTO;
import com.duth.engapp.entity.Dictionary;
import com.duth.engapp.payload.ApiResponse;
import com.duth.engapp.repository.DictionaryRepository;
import com.duth.engapp.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController extends RootController{
    DictionaryRepository dictionaryRepository;
    DictionaryService dictionaryService;
    @Autowired
    public DictionaryController(DictionaryRepository dictionaryRepository, DictionaryService dictionaryService) {
        this.dictionaryRepository = dictionaryRepository;
        this.dictionaryService = dictionaryService;
    }
    @GetMapping("/all")
    ApiResponse getPage(@RequestParam(name = "page", required = false, defaultValue = "0") String page){
        ApiResponse response = new ApiResponse(200, "Get word success",null );
        List<DictionaryDTO> dictionaryDTOS = new ArrayList<>();
        try
        {
            int pages = Integer.parseInt(page);
            List<Dictionary> dictionary = dictionaryService.get(pages);
            dictionary.forEach(n->{dictionaryDTOS.add(new DictionaryDTO(n));});
            response.setResult(dictionaryDTOS);
        }catch (Exception ex)
        {
            response.setStatus(404);
            ex.printStackTrace();
            response.setMessage("Page param is valid");
            return response;
        }
        return response;
    }

    @GetMapping("/search")
    ApiResponse searchPage(@RequestParam(name = "word", required = false, defaultValue = "a") String word, @RequestParam(name = "page", required = false, defaultValue = "0") String page){
        ApiResponse response = new ApiResponse(200, "Get word success",null );
        List<DictionaryDTO> dictionaryDTOS = new ArrayList<>();
        try
        {
            int pages = Integer.parseInt(page);
            List<Dictionary> dictionary = dictionaryService.search(word, pages);
            dictionary.forEach(n->{dictionaryDTOS.add(new DictionaryDTO(n));});
            response.setResult(dictionaryDTOS);
        }catch (Exception ex)
        {
            response.setStatus(404);
            ex.printStackTrace();
            response.setMessage("Page param is valid");
            return response;
        }
        return response;
    }
    @GetMapping("/")
    Dictionary getByName(@RequestParam("word") String word) {
        return dictionaryRepository.findDictionariesByWord(word);
    }
}
