/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessthenumber.data;

import com.mycompany.guessthenumber.models.Game;
import com.mycompany.guessthenumber.models.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mike
 */
@Repository
@Profile("database")
public class GameDatabaseDao implements GameDao{
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public GameDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Game> getallgames() {
        final String sql = "SELECT gameId, answer, isFinish FROM GAME;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public List<Round> getallroundsforgame(int gameId) {
        final String sql = "SELECT roundId, guess, num, result, gameId" 
                          + " FROM round"
                          + " WHERE gameId = ?;";
        return jdbcTemplate.query(sql, new RoundMapper(), gameId);
    }

    @Override
    public Game creatgame(int answer11) {
        final String sql = "INSERT INTO GAME(answer, isFinish) VALUES(?,?);";
        int asnwer = answer11;
        boolean isFinish =false;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, asnwer);
            statement.setBoolean(2, isFinish);
            return statement;

        }, keyHolder);
        Game temp = new Game();
        temp.setId(keyHolder.getKey().intValue());
        temp.setanswer(asnwer);
        temp.setFinished(isFinish);
        return temp;
        
    }

    @Override
    public void updategame(int id) {
        final String sql = "UPDATE GAME SET "
                + " isFinish = ?"
                + " WHERE gameId = ?;";
        final String sq2 = "SELECT * FROM GAME"+ " WHERE gameId = ?;";
        Game temp = jdbcTemplate.queryForObject(sq2, new GameMapper(), id );
        temp.setFinished(true);
        jdbcTemplate.update(sql,temp.isFinished(),id);


    }

    @Override
    public Round addround(int guess, Game game1, String result1) {
        final String sql = "INSERT INTO Round(guess, num, result, gameId) VALUES(?,?,?,?);";
        String result;
        int number;
        int gameId = game1.getId();
        List<Round> list_rounds_for_game = getallroundsforgame(gameId);
        if(list_rounds_for_game.size()>0)
        {
            number = list_rounds_for_game.size()+1;
        }
        else
        {
            number = 1;
        }
        result = result1;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, guess);
            statement.setInt(2, number);
            statement.setString(3, result);
            statement.setInt(4, gameId);
            return statement;

        }, keyHolder);
        Round temp = new Round();
        temp.setId(keyHolder.getKey().intValue());
        temp.setguess(guess);
        temp.setnumber(number);
        temp.setresult(result);
        temp.setgameId(gameId);
        return temp;
            
    }
    
    @Override
    public Game getasnwer(int gameId) {
               final String sql = "SELECT gameId, answer, isFinish FROM GAME"
                + " WHERE gameId = ?;";
               return jdbcTemplate.queryForObject(sql,new GameMapper(),gameId);
    }

    @Override
    public void deletebyid(int gameId) {
        final String DELETE_COURSE = "DELETE FROM game WHERE gameId = ?";
        jdbcTemplate.update(DELETE_COURSE, gameId);
    }

    @Override
    public void deleteroundbyid(int roundId) {
        final String DELETE_COURSE = "DELETE FROM round WHERE roundId = ?";
        jdbcTemplate.update(DELETE_COURSE, roundId);
    }

    @Override
    public List<Round> getallrounds() {
                final String sql = "SELECT * FROM round;";
        return jdbcTemplate.query(sql, new RoundMapper());
    }

      private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setId(rs.getInt("gameId"));
            game.setanswer(rs.getInt("answer"));
            game.setFinished(rs.getBoolean("isFinish"));
            return game;
        }
    }
      private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setId(rs.getInt("roundId"));
            round.setguess(rs.getInt("guess"));
            round.setnumber(rs.getInt("num"));
            round.setresult(rs.getString("result"));
            round.setgameId(rs.getInt("gameId"));
            return round;
        }
    }
    
    
}

