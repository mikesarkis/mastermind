/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessthenumber.data;

import com.mycompany.guessthenumber.models.Game;
import com.mycompany.guessthenumber.models.Round;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Mike
 */
@SpringBootTest()
public class GameDatabaseDaoTest {
     @Autowired
     GameDao dao;
    
    public GameDatabaseDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
     /*   List<Game> games = dao.getallgames();
        for(Game current: games)
        {
            dao.deletebyid(current.getId());
        }
        List<Round> rounds = dao.getallrounds();
        for(Round current: rounds)
        {
            dao.deleteroundbyid(current.getId());
        } */
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testcreategame() {
        
        Game game = new Game();
        game.setFinished(false);
        game.setId(1);
        game.setanswer(4587);
        Game testgame = dao.creatgame(4587);
        game.setanswer(testgame.getanswer());  
        assertEquals(game,testgame);
    }
    @Test
    public void testupdatetegame() {
        Game game = new Game();
        game.setFinished(true);
        game.setanswer(5879);
        game.setId(1);     
        Game testgame = dao.creatgame(5879);
        dao.updategame(1);    
        assertEquals(game,testgame);
    }
    @Test
    public void testgetanswer() {
        dao.creatgame(5871);
        Game testgame = dao.getasnwer(1);
        Game game = new Game();
        game.setFinished(false);
        game.setId(1);
        game.setanswer(5871);
        assertEquals(game,testgame);
    }
    @Test
    public void testgetallgames() {
        Game game1 = dao.creatgame(8923);
        Game game2 = dao.creatgame(7631);
        List<Game> list1= dao.getallgames();
        assertEquals(2,list1.size());
        assertTrue(list1.contains(game1));
        assertTrue(list1.contains(game2));
    }
    @Test
    public void testaddround() {
        Game game = new Game();
        game.setFinished(false);
        game.setId(1);
        game.setanswer(8452);
        int guess = 8453;
        String result= "e:3:p:0";
        Round round1 = new Round();
        round1.setId(1);
        round1.setgameId(1);
        round1.setguess(8453);
        round1.setnumber(1);
        round1.setresult("e:3:p:0");
        Round temp_round = dao.addround(guess, game, result);
        assertEquals(round1,temp_round);
    }
    @Test
    public void testdeletbyid()
    {
       Game game = new Game();
       game.setFinished(false);
       game.setId(1);
       game.setanswer(2314);
       Game game2 = new Game();
       game2.setFinished(false);
       game2.setId(2);
       game2.setanswer(3247);
       dao.creatgame(2314);
       dao.creatgame(3247);
       dao.deletebyid(1);
       List<Game> list1 = dao.getallgames();
       assertEquals(1,list1.size());
       assertTrue(list1.contains(game2));
       list1 = dao.getallgames();
       dao.deletebyid(2);
       assertTrue(list1.isEmpty());    
    }
    
    
}
