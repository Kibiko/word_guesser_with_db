package com.demos.bnta.word_guesser.models;

import java.util.ArrayList;

public class LetterList {

    private ArrayList<String> letters;

    public LetterList(ArrayList<String> letters) {
        this.letters = letters;
    }

    public LetterList() {
    }

    public ArrayList<String> getLetters() {
        return letters;
    }

    public void setLetters(ArrayList<String> letters) {
        this.letters = letters;
    }
}
