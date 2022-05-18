package com.example.timeoff.models;

import java.util.ArrayList;

public class History {

    public String roomName;
    public String food;
    public String games;

    public History(String roomName, String food, String games) {
        this.roomName = roomName;
        this.food = food;
        this.games = games;
    }

    public History() {}

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getGames() {
        return games;
    }

    public void setGames(String games) {
        this.games = games;
    }
}
