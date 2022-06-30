package com.duth.engapp.controller;

import com.duth.engapp.DTO.DictionaryDTO;
import com.duth.engapp.dao.AddMean;
import com.duth.engapp.dao.DeleteMean;
import com.duth.engapp.entity.Dictionary;
import com.duth.engapp.entity.Meaning;
import com.duth.engapp.entity.TypesOfWord;
import com.duth.engapp.payload.ApiResponse;
import com.duth.engapp.repository.DictionaryRepository;
import com.duth.engapp.repository.MeaningRepository;
import com.duth.engapp.repository.TypeOfWordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/meaning")
public class MeaningController extends RootController{
    MeaningRepository meaningRepository;
    DictionaryRepository dictionaryRepository;
    TypeOfWordRepository typeOfWordRepository;
    Logger logger = LoggerFactory.getLogger(MeaningController.class);
    @Autowired
    public MeaningController(MeaningRepository meaningRepository,
                             DictionaryRepository dictionaryRepository,
                             TypeOfWordRepository typeOfWordRepository)
    {
        this.meaningRepository = meaningRepository;
        this.dictionaryRepository = dictionaryRepository;
        this.typeOfWordRepository = typeOfWordRepository;
    }
    @GetMapping("/get")
    ApiResponse get(@RequestParam("word") String word) {
        word = word.trim().toLowerCase(Locale.ROOT).replaceAll("\\s+", " ");
        ApiResponse response = new ApiResponse(200, "Get word success",null );
        Dictionary dictionary = dictionaryRepository.findDictionariesByWord(word);
        if(dictionary == null)
        {
            response.setStatus(404);
            response.setMessage("Not found dictionary");
        }
        else
        {
            response.setResult(new DictionaryDTO(dictionary));
            logger.info(dictionary.toString());
            for(Meaning mean: dictionary.getMeanings())
            {
                logger.info(mean.toString());
            }
        }
        return response;
    }
    @GetMapping("/")
    ApiResponse getByName(@RequestParam("word") String word) {
        word = word.trim().toLowerCase(Locale.ROOT).replaceAll("\\s+", " ");
        Dictionary dictionary = dictionaryRepository.findDictionariesByWord(word);
        ApiResponse response = new ApiResponse(200, "Get word success",null );
        if(dictionary == null)
        {
            response.setStatus(404);
            response.setMessage("Not found dictionary");
        }
        assert dictionary != null;
        response.setResult(new DictionaryDTO(dictionary));
        logger.info(response.toString());
        return response;
    }
    @DeleteMapping("/")
    ApiResponse delete(@RequestBody DeleteMean id) {
        ApiResponse response = new ApiResponse(200, "Delete success",null );
        try
        {
            Meaning meaning = meaningRepository.findMeaningById(Long.valueOf(id.getId()));
            response.setResult(meaning);
            meaningRepository.delete(meaning);
        }catch (Exception ex)
        {
            response.setStatus(500);
            response.setMessage("FAILED SERVER");
            logger.error(ex.toString());
        }
        return response;
    }
    @PostMapping("/new")
    ApiResponse getByName(@Valid @RequestBody AddMean addMean) {
        ApiResponse response = new ApiResponse(200, "Add new meaning success",null );
        Dictionary dictionary = dictionaryRepository.findDictionariesByWord(addMean.getWord().trim().toLowerCase(Locale.ROOT).replaceAll("\\s+", " "));
        TypesOfWord typesOfWord = typeOfWordRepository.findByShortcut(addMean.getType().trim().toLowerCase(Locale.ROOT).replaceAll("\\s+", " "));
        List<Meaning> meanings = meaningRepository.findMeaningByDictionaryidAndMeanAndTypeid(dictionary, addMean.getMean(), typesOfWord);
        if(dictionary == null)
        {
            response.setStatus(404);
            response.setMessage("Not found dictionary");
            response.setResult(null);
        }
        if(meanings.size() != 0)
        {
            response.setStatus(404);
            response.setMessage("Meaning already exists");
            response.setResult(meanings);
        }
        else if(typesOfWord == null)
        {
            response.setStatus(404);
            response.setMessage("Not found type of word");
            response.setResult(null);
        }
        else
        {
            Meaning meaning = new Meaning(dictionary, addMean.getMean().trim(), typesOfWord);
            try{
                meaning = meaningRepository.save(meaning);
                dictionary = dictionaryRepository.findDictionariesByWord(addMean.getWord());
                response.setResult(dictionary);
            }catch(Exception ex)
            {
                logger.info("Error on save meaning");
                ex.printStackTrace();
                response.setStatus(500);
                response.setMessage("Server error");
                response.setResult(null);
                return response;
            }
        }
        return response;
    }
}
