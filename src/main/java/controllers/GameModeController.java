package controllers;
import alertQueue.Alert;
import httpStatus.HttpStatus;
import messageFactory.TicTacToeMessageFactory;
import parsers.ParametersParser;
import requests.Request;
import specialCharacters.EscapeCharacters;
import validators.TicTacToeValidator;

public class GameModeController implements Controller{
    private Alert alert = Alert.getInstance();
    private ParametersParser parser = new ParametersParser();

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
        String gameMode = parser.parseForValue(request.getParameters(), "gamemode");
        boolean validGameMode = validator.validGameMode(gameMode);

        if (validGameMode) {
            response += "Location: http://localhost:5000/first-player-name" + EscapeCharacters.newline + EscapeCharacters.newline;
        } else {
            response += "Location: http://localhost:5000/game-mode" + EscapeCharacters.newline + EscapeCharacters.newline;
            alert.add(gameMode + " is not a valid game mode");
        }
        return response.getBytes();
    }

    private String formatIntoHtml(String message) {
        String alertMessage = showAlert();
        String html = "<!DOCTYPE html>"
                    + "<html><head></head><body><p>"
                    + message
                    + "</p><form action=\"http://localhost:5000/game-mode\" method=\"post\"><input name=\"gamemode\" type=\"radio\" value=\"hh\">Human vs Human</input>"
                    + "<input name=\"gamemode\"type=\"radio\" value=\"hc\"> Human vs Computer</input>"
                    + "<input type=\"submit\"/></form>"
                    + alertMessage
                    + "</body></html>";
        return html;
    }

    private String showAlert() {
        String messageAlert = alert.removeAndReturnFirst();
        String html = "";
        if (messageAlert != null) {
            html += "<div class=\"alert\">"
                    + messageAlert
                    + "</div>";
        }

        return html;
    }
}
