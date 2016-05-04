package controllers;
import httpStatus.HttpStatus;
import messageFactory.TicTacToeMessageFactory;
import requests.Request;
import specialCharacters.EscapeCharacters;

public class GameSetupController implements Controller{

    public byte[] handle(Request request) {
        if(request.getHttpVerb().equals("GET")) {
            return get();
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

    private String formatIntoHtml(String message) {
        return "<!DOCTYPE html>"
               + "<html><head></head><body><p>"
               + message
               + "</p><form><input name=\"gamemode\" type=\"radio\" value=\"hh\">Human vs Human</input>"
               + "<input name=\"gamemode\"type=\"radio\" value=\"hc\"> Human vs Computer</input>"
               + "<input type=\"submit\"/></form>"
               + "</body></html>";
    }
}
