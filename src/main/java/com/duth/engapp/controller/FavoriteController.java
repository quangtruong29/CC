package com.duth.engapp.controller;

import com.duth.engapp.DTO.DictionaryDTO;
import com.duth.engapp.DTO.FavoriteRemoveDTO;
import com.duth.engapp.entity.CustomUserDetails;
import com.duth.engapp.entity.Dictionary;
import com.duth.engapp.entity.Favorite;
import com.duth.engapp.entity.User;
import com.duth.engapp.payload.ApiResponse;
import com.duth.engapp.repository.DictionaryRepository;
import com.duth.engapp.repository.FavoriteRepository;
import com.duth.engapp.repository.ScoreRepository;
import com.duth.engapp.repository.UserRepository;
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
@RequestMapping("/favorite")
public class FavoriteController extends RootController {
    ScoreRepository scoreRepository;
    UserRepository userRepository;
    FavoriteRepository favoriteRepository;

    DictionaryRepository dictionaryRepository;
    @Autowired
    public FavoriteController(ScoreRepository scoreRepository, UserRepository userRepository, FavoriteRepository favoriteRepository, DictionaryRepository dictionaryRepository) {
        this.scoreRepository = scoreRepository;
        this.userRepository = userRepository;
        this.favoriteRepository = favoriteRepository;
        this.dictionaryRepository = dictionaryRepository;
    }
    Logger logger = LoggerFactory.getLogger(FavoriteController.class);
    @GetMapping("/get")
    public ApiResponse get()
    {
        CustomUserDetails userDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByEmail(userDetails.getUser().getEmail());
        ApiResponse response = new ApiResponse(200, "GET success",null, null );
        List<Favorite> favorites = favoriteRepository.findFavoriteByUserid(user);
        List<DictionaryDTO> favoriteDTOS = favorites.stream()
                .map(Favorite::getDictionaryid).map(DictionaryDTO::new).toList();
        response.setResult(favoriteDTOS);
        return response;
    }
    @DeleteMapping("/delete")
    @Transactional
    public ApiResponse delete(@Valid @RequestBody FavoriteRemoveDTO favoriteRemoveDTO)
    {
        ApiResponse response = new ApiResponse(200, "Delete success",null, null );
        CustomUserDetails userDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByEmail(userDetails.getUser().getEmail());
        Long dictionaryID = Long.valueOf(favoriteRemoveDTO.getId());
        Optional<Dictionary> dictionary = dictionaryRepository.findById(dictionaryID);
        if(dictionary.isEmpty())
        {
            response.setStatus(404);
            response.setMessage("Dictionary ID is not exist");
            return response;
        }
        if(!favoriteRepository.existsFavoriteByUseridAndDictionaryid(user, dictionary.get()))
        {
            response.setStatus(404);
            response.setMessage("Favorite is not exist");
            return response;
        }
        try {
            List<Favorite> favorites = favoriteRepository.deleteAllByUseridAndDictionaryid(user, dictionary.get());
            List<DictionaryDTO> favoriteDTOS = favorites.stream()
                    .map(Favorite::getDictionaryid).map(DictionaryDTO::new).toList();
            response.setResult(favoriteDTOS);
        }catch(Exception ex)
        {
            ex.printStackTrace();
            response.setStatus(500);
            response.setMessage("SERVER ERROR");
            return response;
        }
        return response;
    }
    @PostMapping("/new")
    public ApiResponse newFavorite(@Valid @RequestBody FavoriteRemoveDTO favoriteRemoveDTO)
    {
        ApiResponse response = new ApiResponse(200, "Create success",null, null );
        CustomUserDetails userDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByEmail(userDetails.getUser().getEmail());
        Long dictionaryID = Long.valueOf(favoriteRemoveDTO.getId());
        Optional<Dictionary> dictionary = dictionaryRepository.findById(dictionaryID);
        if(dictionary.isEmpty())
        {
            response.setStatus(404);
            response.setMessage("Dictionary ID is not exist");
            return response;
        }

        if(favoriteRepository.existsFavoriteByUseridAndDictionaryid(user, dictionary.get()))
        {
            response.setStatus(404);
            response.setMessage("Favorite is exist");
            return response;
        }
        Favorite favorite = new Favorite();
        favorite.setDictionaryid(dictionary.get());
        favorite.setUserid(user);
        try {
            favoriteRepository.save(favorite);
            List<Favorite> favorites =  favoriteRepository.findFavoriteByUseridAndDictionaryid(user, dictionary.get());
            List<DictionaryDTO> favoriteDTOS = favorites.stream()
                    .map(Favorite::getDictionaryid).map(DictionaryDTO::new).toList();
            response.setResult(favoriteDTOS);
        }catch(Exception ex)
        {
            ex.printStackTrace();
            response.setStatus(500);
            response.setMessage("SERVER ERROR");
            return response;
        }
        return response;
    }
}
