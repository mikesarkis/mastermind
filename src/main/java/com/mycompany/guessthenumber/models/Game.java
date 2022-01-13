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
public class Game {
    private int gameId;
    private int answer;
    private boolean isFinish;
    
    public int getId() {
        return gameId;
    }

@Override
public int hashCode() {
    int hash = 7;
    hash = 89 * hash + Objects.hashCode(this.gameId);
    hash = 89 * hash + Objects.hashCode(this.answer);
    hash = 89 * hash + Objects.hashCode(this.isFinish);
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
    final Game other = (Game) obj;
    if (!Objects.equals(this.gameId, other.gameId)) {
        return false;
    }
    if (!Objects.equals(this.answer, other.answer)) {
        return false;
    }
    if (!Objects.equals(this.isFinish, other.isFinish)) {
        return false;
    }
    return true;
}

    public void setId(int id) {
        this.gameId = id;
    }


    public int getanswer() {
        return answer;
    }

    public void setanswer(int answer) {
        this.answer = answer;
    }

    public boolean isFinished() {
        return isFinish;
    }

    public void setFinished(boolean finish) {
        this.isFinish = finish;
    }
    
}
