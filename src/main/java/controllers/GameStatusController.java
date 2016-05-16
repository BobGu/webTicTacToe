package controllers;

import gameStatus.GameStatus;
import httpStatus.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import requests.Request;
import responseBuilders.ResponseBuilder;
import specialCharacters.EscapeCharacters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GameStatusController implements Controller{
    private GameStatus gameStatus;
    private ResponseBuilder builder;

    public GameStatusController(GameStatus gameStatus, ResponseBuilder builder) {
        this.gameStatus = gameStatus;
        this.builder = builder;
    }

    public byte[] handle(Request request) throws IOException {
        if(request.getHttpVerb().equals("POST")) {
            try {
                return post(request);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new byte[4];
    }

    public byte[] post(Request request) throws IOException, JSONException {
        ArrayList<Object> board = getBoardFromJson(request.getParameters());
        boolean isGameWon = gameStatus.gameWon(board);
        String jsonResponse = createJsonResponse(isGameWon);
        builder.addStatus(HttpStatus.OKAY.getResponseCode());
        builder.addBodyContents(jsonResponse.getBytes());
        return builder.getResponse();
    }

    private ArrayList<Object> getBoardFromJson(String json) throws IOException {
        HashMap<String, ArrayList<Object>> convertedJson = toHashMap(json);
        ArrayList<Object> board = convertedJson.get("board");
        return stringSpacesToIntegerSpaces(board);
    }

    private HashMap<String, ArrayList<Object>> toHashMap(String json) throws IOException, JSONException {
        HashMap<String, ArrayList<Object>> map = new HashMap<String, ArrayList<Object>>();
        JSONObject jObject = new JSONObject(json);
        Iterator<String> keys = jObject.keys();

        while( keys.hasNext() ){
            String key = (String)keys.next();
            JSONArray board = jObject.getJSONArray(key);
            ArrayList<Object> newBoard = jsonArrayToList(board);
            map.put(key, newBoard);
        }

        return map;
    }

    private ArrayList<Object> jsonArrayToList(JSONArray jsonArray) throws JSONException {
        ArrayList<Object> list = new ArrayList<Object>();
        for(int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }

    private ArrayList<Object> stringSpacesToIntegerSpaces(ArrayList<Object> board) {
        ArrayList<Object> newBoard = new ArrayList<Object>();

        for(Object space : board) {
            if (tryParseInt(space)) {
                newBoard.add(Integer.parseInt(space.toString()));
            } else {
                newBoard.add(space);
            }
        }
        return newBoard;
    }

    boolean tryParseInt(Object value) {
        try {
            Integer.parseInt(value.toString());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String createJsonResponse(boolean isGameWon) {
        return  "{\"response\":{\"gameStatus\":"  +
                "{\"gameWon\":\"" + isGameWon + "\"}}}";
    }

}
