package com.demos.bnta.word_guesser.models;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity(name = "games")
public class Game {

    @Id //automatically makes column id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generates an ID using certain strategy
    private Long id;

    @Column(name = "word") //by default, it would take the property's name as column name
    private String word;

    @Column
    private int guesses;

    @Column
    private boolean complete;

    @Column
    private String currentWord;

    @Column
    private ArrayList<String> guessedLetters;

    public Game(String word) {
        this.guesses = 0;
        this.complete = false;
        this.word = word;
    }

    public Game() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getGuesses() {
        return guesses;
    }

    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public ArrayList<String> getGuessedLetters() {
        return guessedLetters;
    }

    public void setGuessedLetters(ArrayList<String> guessedLetters) {
        this.guessedLetters = guessedLetters;
    }
}
