/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessthenumber.models;

import java.util.Objects;

/**
 *
 * @author Mike
 */
public class Round {
    private int roundId;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.roundId);
        hash = 89 * hash + Objects.hashCode(this.number);
        hash = 89 * hash + Objects.hashCode(this.guess);
        hash = 89 * hash + Objects.hashCode(this.result);
        hash = 89 * hash + Objects.hashCode(this.gameId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (!Objects.equals(this.roundId, other.roundId)) {
          return false;
        }
        if (!Objects.equals(this.number, other.number)) {
          return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
         return false;
        }
        if (!Objects.equals(this.result, other.result)) {
          return false;
        }
        if (!Objects.equals(this.gameId, other.gameId)) {
          return false;
        }
         return true;
        }
    private int number;
    private int guess;
    private String result;
    private int gameId;
    
    public int getId() {
        return roundId;
    }

    public void setId(int id) {
        this.roundId = id;
    }


    public int getnumber() {
        return number;
    }

    public void setnumber(int number) {
        this.number = number;
    }
        public int getguess() {
        return guess;
    }
    public void setguess(int guess) {
        this.guess = guess;
    }

    public String getresult() {
        return result;
    }
    public void setresult(String result) {
        this.result = result;
    }
    public void setgameId(int id)
    {
        this.gameId= id;
    }
    
    public int getgameId() {
        return gameId;
    }
    
    
}
