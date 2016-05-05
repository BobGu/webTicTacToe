package controllers;
import httpStatus.HttpStatus;
import messageFactory.TicTacToeMessageFactory;
import requests.Request;
import specialCharacters.EscapeCharacters;
import validators.TicTacToeValidator;

public class GameModeController implements Controller{

    public byte[] handle(Request request) {
        if(request.getHttpVerb().equals("GET")) {
            return get();
        } else if(request.getHttpVerb().equals("POST")) {
            return post(request);
        }
        return new byte[4];
    }

    private byte[] get() {
        String responseHeader = HttpStatus.OKAY.getResponseCode() + EscapeCharacters.newline;
        responseHeader += "Content-Type: text/html" + EscapeCharacters.newline + EscapeCharacters.newline;
        TicTacToeMessageFactory ticTacToeMessageFactory = new TicTacToeMessageFactory();
        String gameMessage = ticTacToeMessageFactory.gameMode();
        String responseBody = formatIntoHtml(gameMessage);
        String response = responseHeader + responseBody;
        return response.getBytes();
    }

    private byte[] post(Request request) {
        String response = HttpStatus.REDIRECT.getResponseCode() + EscapeCharacters.newline;
        TicTacToeValidator validator = new TicTacToeValidator();
        boolean validGameMode = validator.validGameMode(request.getParameters());
        if (validGameMode) {
            response += "Location: localhost:5000/first-player-name" + EscapeCharacters.newline + EscapeCharacters.newline;
        } else {
            response += "Location: localhost:5000/game-mode" + EscapeCharacters.newline + EscapeCharacters.newline;
        }
        return response.getBytes();
    }

    private String formatIntoHtml(String message) {
        return "<!DOCTYPE html>"
               + "<html><head></head><body><p>"
               + message
               + "</p><form action=\"localhost:5000\" ><input name=\"gamemode\" type=\"radio\" value=\"hh\">Human vs Human</input>"
               + "<input name=\"gamemode\"type=\"radio\" value=\"hc\"> Human vs Computer</input>"
               + "<input name=\"submit\" type=\"submit\" method=\"post\"/></form>"
               + "</body></html>";
    }
}
