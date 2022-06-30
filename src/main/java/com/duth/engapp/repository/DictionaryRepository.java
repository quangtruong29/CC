package com.duth.engapp.repository;

import com.duth.engapp.entity.Dictionary;
import com.duth.engapp.entity.Meaning;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {

    Dictionary findDictionariesByWord(String word);

    @Cacheable("PageDictionary")
    Page<Dictionary> findAll(Pageable pageable);
    @Cacheable("PageDictionary")
    Page<Dictionary> findAllByWordStartingWith(String word, Pageable pageable);

    @Cacheable("PageDictionary")
    Page<Dictionary> findByMeaningsIn(Collection<Set<Meaning>> meanings, Pageable pageable);

}
