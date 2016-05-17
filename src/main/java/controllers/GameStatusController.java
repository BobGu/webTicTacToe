package controllers;

import converters.Converter;
import converters.JsonConverter;
import gameStatus.GameStatus;
import httpStatus.HttpStatus;
import org.json.JSONException;
import requests.Request;
import responseBuilders.ResponseBuilder;
import responseBuilders.json.JsonBuilder;

import java.io.IOException;
import java.util.HashMap;

public class GameStatusController implements Controller{
    private GameStatus gameStatus;
    private ResponseBuilder builder;
    private Converter converter;
    private JsonBuilder jsonBuilder;

    public GameStatusController(GameStatus gameStatus, ResponseBuilder builder, Converter converter, JsonBuilder jsonBuilder) {
        this.gameStatus = gameStatus;
        this.builder = builder;
        this.converter = converter;
        this.jsonBuilder = jsonBuilder;
    }

    public byte[] handle(Request request) throws IOException {
        if(request.getHttpVerb().equals("POST")) {
            try {
                return askIfGameIsWon(request);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new byte[4];
    }

    public byte[] askIfGameIsWon(Request request) throws IOException, JSONException {
        HashMap<String, Object> board = converter.toHashMap(request.getParameters());
        boolean isGameWon = gameStatus.gameWon(board.get("board"));
        String jsonResponse = (String) jsonBuilder.gameWon(isGameWon);
        builder.addStatus(HttpStatus.OKAY.getResponseCode());
        builder.addBodyContents(jsonResponse.getBytes());
        return builder.getResponse();
    }


}
