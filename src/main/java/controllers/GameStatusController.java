package controllers;

import gameStatus.GameStatus;
import requests.Request;

import java.util.ArrayList;

public class GameStatusController implements Controller{
    private GameStatus gameStatus;

    public GameStatusController(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public byte[] handle(Request request) {
        if(request.equals("POST")) {
            return post(request);
        }
        return new byte[4];
    }

    public byte[] post(Request request) {
        //gameStatus.gameWon(request)
        //"\"success\": {\"status\": \"200\", \"responseText\": '{\"response\":{\"gameStatus\":{\"gameWon\":\"false\"}}}'";
        return new byte[4];
    }

}
