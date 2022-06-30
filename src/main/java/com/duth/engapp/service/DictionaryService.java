package com.duth.engapp.service;

import com.duth.engapp.entity.Dictionary;
import com.duth.engapp.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryService {
    final int page = 20;
    DictionaryRepository dictionaryRepository;
    @Autowired
    public DictionaryService(DictionaryRepository dictionaryRepository)
    {
        this.dictionaryRepository = dictionaryRepository;
    }
    public List<Dictionary> get(int size)
    {
        Page<Dictionary> dictionaries = dictionaryRepository.findAll(PageRequest.of(size, page));
        return dictionaries.get().toList();
    }
    public List<Dictionary> search(String search, int size)
    {
        Page<Dictionary> dictionaries = dictionaryRepository.findAllByWordStartingWith(search, PageRequest.of(size, page));
        return dictionaries.get().toList();
    }
}
