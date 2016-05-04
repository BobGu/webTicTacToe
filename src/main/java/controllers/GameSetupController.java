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
        String responseBody = "<!DOCTYPE html>"
                + "<html><head></head><body><p>"
                + ticTacToeMessageFactory.gameMode()
                + "</p></body></html>";
        String response = responseHeader + responseBody;
        return response.getBytes();
    }
}
