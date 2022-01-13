/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessthenumber.data;

import java.util.List;
import com.mycompany.guessthenumber.models.Game;
import com.mycompany.guessthenumber.models.Round;
/**
 *
 * @author Mike
 */
public interface GameDao {
    public List<Game> getallgames();
    public List<Round> getallroundsforgame(int gameId);
    public Game creatgame(int answer);
    public void updategame(int id);
    public Round addround(int guess, Game game1,  String result1);
    public Game getasnwer(int gameId);
    public void deletebyid(int gameId);
    public void deleteroundbyid(int roundId);
    public List<Round> getallrounds();
    
}
