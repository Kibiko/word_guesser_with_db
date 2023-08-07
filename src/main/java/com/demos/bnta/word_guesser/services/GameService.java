package com.demos.bnta.word_guesser.services;

import com.demos.bnta.word_guesser.models.*;
import com.demos.bnta.word_guesser.repositories.GameRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {


    @Autowired
    GameRepository gameRepository;

    @Autowired
    WordService wordService;

//    private String currentWord;
//    private ArrayList<String> guessedLetters;

    public GameService() {
    }


//    public String getCurrentWord() {
//        return currentWord;
//    }
//
//    public void setCurrentWord(String currentWord) {
//        this.currentWord = currentWord;
//    }

//    public ArrayList<String> getGuessedLetters() {
//        return guessedLetters;
//    }
//
//    public void setGuessedLetters(ArrayList<String> guessedLetters) {
//        this.guessedLetters = guessedLetters;
//    }

    public Reply processGuess(Guess guess, Long id){

        // Find the correct game
        Game game = gameRepository.findById(id).get();

        // Check if game is already complete
        if (game.isComplete()){
            return new Reply(
                    false,
                    game.getWord(),
                    String.format("Already finished game %d", game.getId())
            );
        }

        // Check if letter has been guessed already
        if (game.getGuessedLetters().contains(guess.getLetter())){
            return new Reply(false, game.getCurrentWord(), String.format("Already guessed %s", guess.getLetter()));
        }

        // Only increment guess count if a new letter is chosen
        incrementGuesses(game);
        // Add letter to previous guesses
        game.getGuessedLetters().add(guess.getLetter());
        gameRepository.save(game);

        // Check for incorrect guess
        if (!game.getWord().contains(guess.getLetter())){
            return new Reply(
                    false,
                    game.getCurrentWord(),
                    String.format("%s is not in the word", guess.getLetter())
            );
        }

        // Handle correct guess
        String runningResult = game.getWord();

        for (Character letter : game.getWord().toCharArray()) {
            if (!game.getGuessedLetters().contains(letter.toString())){
                runningResult = runningResult.replace(letter, '*');
            }
        }

        game.setCurrentWord(runningResult);
        gameRepository.save(game);

        // Check for win
        if (checkWinCondition(game)){
            game.setComplete(true);
            gameRepository.save(game);
            return new Reply(true, game.getCurrentWord(), "You win!");
        } else {
            return new Reply(true, game.getCurrentWord(),
                    String.format("%s is in the word", guess.getLetter()));
        }
    }

    private boolean checkWinCondition(Game game){
        return game.getWord().equals(game.getCurrentWord());
    }

    private void incrementGuesses(Game game){
        game.setGuesses(game.getGuesses() + 1);
        gameRepository.save(game);
    }

    public Reply startNewGame(){
        String targetWord = wordService.getRandomWord();
        Game game = new Game(targetWord);
        game.setCurrentWord(Strings.repeat("*", targetWord.length()));
        game.setGuessedLetters( new ArrayList<>());
        gameRepository.save(game);
        return new Reply(
                false,
                game.getCurrentWord(),
                String.format("Started new game with id %d", game.getId())
        );
    }

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(Long id){ //may or may not be null
        return gameRepository.findById(id);
    }

}
