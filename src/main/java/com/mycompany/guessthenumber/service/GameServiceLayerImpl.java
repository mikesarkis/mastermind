package com.mycompany.guessthenumber.service;
import com.mycompany.guessthenumber.data.GameDao;
import com.mycompany.guessthenumber.models.Game;
import com.mycompany.guessthenumber.models.Round;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mike
 */
@Repository
public class GameServiceLayerImpl implements GameServiceLayer{
    private  final  GameDao dao;
    public GameServiceLayerImpl(GameDao dao)
    {
        this.dao = dao;
    }
    private int generate_Answer()
    {
        int counter = 0;
        Random gnr = new Random();
        List<Integer> numbers = new ArrayList<>();
        int first;
        int second;
        int third;
        int fourth;
        String numberString= "";
        int number;
        while(counter<4)
        {
            first = gnr.nextInt(9)+1;
            counter ++;
            numbers.add(first);
            int temp= gnr.nextInt(9);
            while(true)
            {
                if(temp== numbers.get(0))
                {
                    temp= gnr.nextInt(9);
                }
                else
                {
                    counter++;
                    second = temp;
                    numbers.add(second);
                    break;
                }
            }
            temp = gnr.nextInt(9);
            while(true)
            {
                if(temp == numbers.get(0)||temp == numbers.get(1))
                {
                    temp= gnr.nextInt(9);
                }
                else
                {
                    counter++;
                    third = temp;
                    numbers.add(third);
                    break;
                }
            }
            temp = gnr.nextInt(9);
            while(true)
            {
                if(temp == numbers.get(0)||temp == numbers.get(1)|| temp == numbers.get(2))
                {
                    temp= gnr.nextInt(9);
                }
                else
                {
                    counter++;
                    fourth = temp;
                    numbers.add(fourth);
                    break;
                }
            }                                
        }
        for (int i= 0 ; i < numbers.size();i++)
        {
            numberString = numberString + String.valueOf(numbers.get(i));
        }
        number  = Integer.parseInt(numberString);
        return number;
    }

    @Override
    public List<Game> getallgames() {
        List<Game> list1 = dao.getallgames();
        List<Game> list_temp = new ArrayList<>();
        for(int i=0;i<list1.size();i++)
        {
            list_temp.add(list1.get(i));
        }
        for(int i=0;i<list_temp.size();i++)
        {
            if(list_temp.get(i).isFinished()==false)
            {
                list_temp.get(i).setanswer(0);
            }
        }
        return list_temp;
    }

    @Override
    public List<Round> getallroundsforgame(int gameId) {
        List<Round> list1 = dao.getallroundsforgame(gameId);
        return list1;
    }

    @Override
    public int creatgame() {
        int answer = generate_Answer();
        Game temp = dao.creatgame(answer);
        return temp.getId();
    }

    @Override
    public Round guess(int guess, int gameid) {
        Game game_temp = dao.getasnwer(gameid);
        String result = getresult(game_temp, guess);
        Round round_temp = dao.addround(guess, game_temp, result);
        if(round_temp.getresult().equals("e:4:p:0"))
            {
                 dao.updategame(gameid);
            }
        return round_temp;
    }

    @Override
    public String getGame(int gameId) {
        Game temp =dao.getasnwer(gameId);
        String respons;
        if(temp.isFinished())
        {
            respons = "the game id is "+ temp.getId() + " and the answer is "+temp.getanswer();
        }
        else
        {
            respons = "the game id is "+ temp.getId() + " but since the game is still in progress you can't see the asnwer ";
        }
        return respons;
            
    }
    @Override
    public String getresult(Game game1, int guess) {
        int exact;
        int partial;
        String message;
        exact = getallexactmatchnumber(game1, guess);
        partial = getallpartialmatchnumber(game1,guess);
        String exactstring = String.valueOf(exact);
        String partialstring = String.valueOf(partial);
        if(exact == 4)
        {
           message = "e:4:p:0"; 
        }
        else
            message = "e:"+ exactstring + ":p:"+ partialstring;
                   
        return message;
    }
    private String checkstatefordigit(Game game1, int number, int index)
    {
        boolean partial_match = false;
        boolean exact_match = false;
        List<Integer> list1 = new  ArrayList<>();
        int temp = game1.getanswer();
        while(temp>0)
        {
            list1.add(temp%10);
            temp = temp/10;
        }
        Collections.reverse(list1);
        for(int i=0; i<list1.size();i++)
        {
            if(number == list1.get(i) && index == i)
            {
                exact_match  = true;
            }
            else
                if(number == list1.get(i))
                {
                    partial_match = true;
                }
        }
        if(exact_match)
        {
            return "exact match";
        }
        else
            if(partial_match)
                return"partial match";
            else 
                return "no match";
        
    }
    private int getallexactmatchnumber(Game game1, int guess)
    {
        int counter =0;
        List<Integer> list_round = new  ArrayList<>();
        int temp_guess = guess;
        while(temp_guess>0)
        {
            list_round.add(temp_guess%10);
            temp_guess = temp_guess/10;
        }
        Collections.reverse(list_round);
        for(int i =0; i<list_round.size();i++)
        {
           if ("exact match".equals(checkstatefordigit( game1,  list_round.get(i), i)))
           {
            counter++;
           }
        }
        return counter;
        
    }
    private int getallpartialmatchnumber(Game game1, int guess)
    {
        int counter =0;
        List<Integer> list_round = new  ArrayList<>();
        int temp_guess = guess;
        while(temp_guess>0)
        {
            list_round.add(temp_guess%10);
            temp_guess = temp_guess/10;
        }
        Collections.reverse(list_round);
        for(int i =0; i<list_round.size();i++)
        {
           if ("partial match".equals(checkstatefordigit( game1,  list_round.get(i), i)))
           {
            counter++;
           }
        }
        return counter;
        
    }
    
}
