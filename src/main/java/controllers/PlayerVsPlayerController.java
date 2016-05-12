package controllers;

import httpStatus.HttpStatus;
import readers.Reader;
import requests.Request;
import responseBuilders.ResponseHeaderBuilder;
import specialCharacters.EscapeCharacters;

import java.io.IOException;

public class PlayerVsPlayerController implements Controller {
    private Reader reader;
    private ResponseHeaderBuilder headerBuilder;

    public PlayerVsPlayerController(Reader reader, ResponseHeaderBuilder headerBuilder) {
        this.reader = reader;
        this.headerBuilder = headerBuilder;
    }

    public byte[] handle(Request request) throws IOException {
        if (request.getHttpVerb().equals("GET")) {
            return getResponse();
        } else {
            return new byte[4];
        }
    }

    public byte[] getResponse() throws IOException {
        headerBuilder.addStatus(HttpStatus.OKAY.getResponseCode());
        headerBuilder.addContentType("text/html");
        byte[] fileContents = reader.read("/board.html");
        String responseBody = new String(fileContents);
        String response = headerBuilder.getResponseHeader() + responseBody;
        return response.getBytes();
    }
}
