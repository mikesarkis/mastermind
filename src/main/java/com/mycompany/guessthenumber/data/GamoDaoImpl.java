/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessthenumber.data;

import com.mycompany.guessthenumber.models.Game;
import com.mycompany.guessthenumber.models.Round;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mike
 */
@Repository
@Profile("memory")
public class GamoDaoImpl implements GameDao {
    private Map<Integer, Game> List_Games = new HashMap<>(); 
    private Map<Integer, Round> List_Rounds = new HashMap<>(); 
    @Override
    public List<Game> getallgames() {
        return new ArrayList<>(List_Games.values());
    }
    public List<Round> getallrounds()
    {
        return new ArrayList<>(List_Rounds.values());
    }

    @Override
    public Game creatgame(int answer) {
        List<Game> list11 = getallgames();
        int number;
        int answer11;
        answer11= answer;
        if(list11.size()>0)
        {
            number = list11.size()+1;
        }
        else
        {
            number = 1;
        }
        Game temp = new Game();
        temp.setId(number);
        temp.setanswer(answer);
        temp.setFinished(false);
        List_Games.put(number, temp);
        return temp;
    }

    @Override
    public void updategame(int id) {
        List_Games.get(id).setFinished(true);
        Game temp = List_Games.get(id);
    }

    @Override
    public Round addround(int guess, Game game1, String result11) {
        List<Round> list_rounds = getallrounds();
        int id;
        int number;
        String result;
        int gameId = game1.getId();
        if(list_rounds.size()>0)
        {
            id = list_rounds.size()+1;
        }
        else
        {
            id = 1;
        }
        List<Round> list_rounds_for_game= list_rounds.stream().filter(c->c.getgameId()== gameId).collect(Collectors.toList());
        if(list_rounds_for_game.size()>0)
        {
            number = list_rounds_for_game.size()+1;
        }
        else
        {
            number = 1;
        }
        result = result11;
        Round roundtemp = new Round();
        roundtemp.setId(id);
        roundtemp.setgameId(gameId);
        roundtemp.setguess(guess);
        roundtemp.setnumber(number);
        roundtemp.setresult(result);
        List_Rounds.put(id, roundtemp);
        return roundtemp;

        
    }

    @Override
    public List<Round> getallroundsforgame(int gameId) {
        List<Round> list_rounds = getallrounds();
        List<Round> list_rounds_per_game = list_rounds.stream().filter(c->c.getgameId()== gameId).collect(Collectors.toList());
        return list_rounds_per_game;
    }

    @Override
    public Game getasnwer(int gameId) {
        return List_Games.get(gameId);
    }

    @Override
    public void deletebyid(int gameId) {
        List_Games.remove(gameId);
    }

    @Override
    public void deleteroundbyid(int roundId) {
       List_Rounds.remove(roundId);
    }


    
}
