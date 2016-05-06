package controllers;

import decorators.htmlDecorator;
import httpStatus.HttpStatus;
import messageFactory.TicTacToeMessageFactory;
import parsers.ParametersParser;
import requests.Request;
import specialCharacters.EscapeCharacters;
import validators.TicTacToeValidator;

public class PlayerNameController implements Controller {
    private TicTacToeMessageFactory messageFactory = new TicTacToeMessageFactory();
    private ParametersParser parser = new ParametersParser();

    public byte[] handle(Request request) {
        if (request.getHttpVerb().equals("GET")) {
            return get();
        } else if (request.getHttpVerb().equals("POST")) {
            return post(request);
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

    private byte[] post(Request request) {
        String responseHeader = HttpStatus.REDIRECT.getResponseCode() + EscapeCharacters.newline;
        TicTacToeValidator validator = new TicTacToeValidator();
        String gameMode = parser.parseForValue(request.getParameters(), "playerName");
        boolean validName = validator.validName(gameMode);

        if (validName) {
            responseHeader += "Location: http://localhost:5000/first-player-piece" + EscapeCharacters.newline + EscapeCharacters.newline;
        } else {
            responseHeader += "Location: http://localhost:5000/first-player-name" + EscapeCharacters.newline + EscapeCharacters.newline;
        }

        return responseHeader.getBytes();
    }
}
