package com.duth.engapp.repository;

import com.duth.engapp.entity.TypesOfWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfWordRepository extends JpaRepository<TypesOfWord, Integer> {
	TypesOfWord findByShortcut(String shortCut);
}
