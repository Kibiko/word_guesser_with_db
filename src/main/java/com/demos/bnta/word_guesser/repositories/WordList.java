package com.demos.bnta.word_guesser.repositories;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Repository
public class WordList {

    private List<String> words;

    public WordList(){
        this.words = Arrays.asList(
                "hello",
                "goodbye",
                "testing",
                "mystery",
                "games",
                "spring",
                "controller",
                "repository"
        );
    }

    public String getRandomWord(){
        Random random = new Random();
        int randomIndex = random.nextInt(this.words.size());
        return this.words.get(randomIndex);
    }

}
