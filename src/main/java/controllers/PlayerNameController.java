package controllers;

import decorators.htmlDecorator;
import httpStatus.HttpStatus;
import messageFactory.TicTacToeMessageFactory;
import requests.Request;
import specialCharacters.EscapeCharacters;

public class PlayerNameController implements Controller {
    private TicTacToeMessageFactory messageFactory = new TicTacToeMessageFactory();

    public byte[] handle(Request request) {
        if (request.getHttpVerb().equals("GET")) {
            return get();
        }
        return new byte[4];
    }

    private byte[] get() {
        String responseHeader = HttpStatus.OKAY.getResponseCode() + EscapeCharacters.newline;
        responseHeader += "Content-Type text/html" + EscapeCharacters.newline + EscapeCharacters.newline;
        String responseBody = htmlDecorator.nameDecorate(messageFactory.askForPlayersName(), "first-player-name");
        String response = responseHeader + responseBody;
        return response.getBytes();
    }
}
