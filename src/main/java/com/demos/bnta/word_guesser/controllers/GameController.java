package com.demos.bnta.word_guesser.controllers;

import com.demos.bnta.word_guesser.models.Game;
import com.demos.bnta.word_guesser.models.Guess;
import com.demos.bnta.word_guesser.models.LetterList;
import com.demos.bnta.word_guesser.models.Reply;
import com.demos.bnta.word_guesser.repositories.GameRepository;
import com.demos.bnta.word_guesser.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    GameService gameService;

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Reply> submitGuess(@RequestBody Guess guess, @PathVariable Long id){
        Reply reply = gameService.processGuess(guess, id);
        return new ResponseEntity<>(reply, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames(){
        List<Game> games = gameService.getAllGames();
        return new ResponseEntity<>(games,HttpStatus.OK);
    }


    @GetMapping(value = "/{gameId}/guessed")
    public ResponseEntity<LetterList> checkGuesses(@PathVariable Long gameId){
        ArrayList<String> guesses = gameService.getGameById(gameId).get().getGuessedLetters();
        LetterList guessedLetters = new LetterList(guesses);
        return new ResponseEntity<>(guessedLetters, HttpStatus.OK);
    }

    @GetMapping(value = "/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable Long gameId) {
        Optional<Game> game = gameService.getGameById(gameId);
        if (game.isPresent()) {
            return new ResponseEntity<>(game.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Reply> startNewGame(){
        Reply reply = gameService.startNewGame();
        return new ResponseEntity<>(reply, HttpStatus.CREATED);
    }

}
