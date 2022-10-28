package com.trilogyed.gamestore.controller;

import com.trilogyed.gamestore.viewModel.GameViewModel;
import com.trilogyed.gamestore.service.GameStoreServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/game")
@CrossOrigin(origins = {"http://localhost:3000"})
public class GameController {

    @Autowired
    GameStoreServiceLayer service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameViewModel createGame(@RequestBody @Valid GameViewModel gameViewModel) {
        gameViewModel = service.createGame(gameViewModel);
        return gameViewModel;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GameViewModel getGameInfo(@PathVariable("id") long gameId) {
        GameViewModel gameViewModel = service.getGame(gameId);
        if (gameViewModel == null) {
            throw new IllegalArgumentException("Game not found for id " + gameId);
        } else {
            return gameViewModel;
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody @Valid GameViewModel gameViewModel) {
        service.updateGame(gameViewModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable("id") int gameId) {
        service.deleteGame(gameId);
    }

    @GetMapping("/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByTitle(@PathVariable("title") String title) {
        List<GameViewModel> gamesByTitle = service.getGameByTitle(title);

        if (gamesByTitle == null || gamesByTitle.isEmpty()) {
            throw new IllegalArgumentException("No games were found with " + title);
        } else {
            return gamesByTitle;
        }
    }

    @GetMapping("/esrbrating/{esrb}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByEsrbRating(@PathVariable("esrb") String esrb) {
        List<GameViewModel> gamesByEsrbRating = service.getGameByEsrb(esrb);

        if (gamesByEsrbRating == null || gamesByEsrbRating.isEmpty()) {
            throw new IllegalArgumentException("No games were found with ESRB Rating " + esrb);
        } else {
            return gamesByEsrbRating;
        }
    }

    @GetMapping("/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByStudio(@PathVariable("studio") String studio) {
        List<GameViewModel> gamesByStudio = service.getGameByStudio(studio);

        if (gamesByStudio == null || gamesByStudio.isEmpty()) {
            throw new IllegalArgumentException("No games were found from " + studio);
        } else {
            return gamesByStudio;
        }
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getAllGames() {
        List<GameViewModel> games = service.getAllGames();

        if (games == null || games.isEmpty()) {
            throw new IllegalArgumentException("No games were found.");
        } else {
            return games;
        }
    }
}
