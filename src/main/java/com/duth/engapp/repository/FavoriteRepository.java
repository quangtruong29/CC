package com.duth.engapp.repository;

import com.duth.engapp.entity.Dictionary;
import com.duth.engapp.entity.Favorite;
import com.duth.engapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>{
    List<Favorite> findFavoriteByUserid(User userid);
    List<Favorite> findFavoriteByUseridAndDictionaryid(User userid, Dictionary dictionaryid);
    boolean existsFavoriteByUseridAndDictionaryid(User userid, Dictionary dictionaryid);
    List<Favorite> deleteAllByUseridAndDictionaryid(User userid, Dictionary dictionaryid);

}