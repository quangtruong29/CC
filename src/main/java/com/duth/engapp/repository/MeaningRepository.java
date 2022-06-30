package com.duth.engapp.repository;

import com.duth.engapp.entity.Dictionary;
import com.duth.engapp.entity.Meaning;
import com.duth.engapp.entity.TypesOfWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MeaningRepository  extends JpaRepository<Meaning, Long> {
    Meaning findMeaningById(Long id);
    List<Meaning> findMeaningByDictionaryid(Dictionary id);
    List<Meaning> findMeaningByDictionaryidAndMeanAndTypeid(Dictionary id, String mean, TypesOfWord typeid);
}