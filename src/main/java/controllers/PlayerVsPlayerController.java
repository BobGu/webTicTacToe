package controllers;

import httpStatus.HttpStatus;
import readers.Reader;
import requests.Request;
import specialCharacters.EscapeCharacters;

import java.io.IOException;

public class PlayerVsPlayerController implements Controller {
    private Reader reader;

    public PlayerVsPlayerController(Reader reader) {
        this.reader = reader;
    }

    public byte[] handle(Request request) throws IOException {
        if (request.getHttpVerb().equals("GET")) {
            return get();
        } else {
            return new byte[4];
        }
    }

    public byte[] get() throws IOException {
        String responseHeader = HttpStatus.OKAY.getResponseCode() + EscapeCharacters.newline;
        responseHeader += "Content-Type: text/html" + EscapeCharacters.newline + EscapeCharacters.newline;
        byte[] fileContents = reader.read("/board.html");
        String responseBody = new String(fileContents);
        String response = responseHeader + responseBody;
        return response.getBytes();
    }
}
