/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessthenumber.controllers;

import com.mycompany.guessthenumber.models.Game;
import com.mycompany.guessthenumber.models.Round;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mycompany.guessthenumber.service.GameServiceLayer;

/**
 *
 * @author Mike
 */
@RestController
@RequestMapping("/api/guessGame")
public class GameControlles {
    private final GameServiceLayer service;
    public GameControlles(GameServiceLayer service)
    {
        this.service = service;
    }
    
@PostMapping("begin")
@ResponseStatus(HttpStatus.CREATED)
public int create() {
    return service.creatgame();
}

@PostMapping("guess")
public Round guess( int id, int guess)
{
    return service.guess(guess, id);
}
@GetMapping("game")
public List<Game> getallgames()
{
    return service.getallgames();
    
}
@GetMapping("game/{id}")
public String getgamebyId(@PathVariable int id)
{
    return service.getGame(id);
}
@GetMapping("rounds/{id}")
public List<Round> getroundsforgame(@PathVariable int id)
{
    return service.getallroundsforgame(id);
}
    
}
