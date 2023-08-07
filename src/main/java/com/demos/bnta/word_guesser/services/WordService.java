package com.demos.bnta.word_guesser.services;

import com.demos.bnta.word_guesser.repositories.WordList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class WordService {

    @Autowired
    WordList wordList;

    public String getRandomWord(){
        return wordList.getRandomWord();
    }

}
