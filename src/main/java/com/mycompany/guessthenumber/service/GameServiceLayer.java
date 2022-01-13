package com.mycompany.guessthenumber.service;
import com.mycompany.guessthenumber.models.Game;
import com.mycompany.guessthenumber.models.Round;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mike
 */
public interface GameServiceLayer {
    public List<Game> getallgames();
    public List<Round> getallroundsforgame(int gameId);
    public int creatgame();
    public Round guess(int guess, int gameid);
    public String getGame(int gameId);
     public String getresult(Game game1, int guess);
    
}
