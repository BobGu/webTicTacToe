package controllers;

import httpStatus.HttpStatus;
import readers.Reader;
import requests.Request;
import responseBuilders.ResponseBuilder;

import java.io.IOException;

public class PlayerVsPlayerController implements Controller {
    private Reader reader;
    private ResponseBuilder responseBuilder;

    public PlayerVsPlayerController(Reader reader, ResponseBuilder responseBuilder ) {
        this.reader = reader;
        this.responseBuilder= responseBuilder;
    }

    public byte[] handle(Request request) throws IOException {
        if (request.getHttpVerb().equals("GET")) {
            return getResponse();
        } else {
            return new byte[4];
        }
    }

    public byte[] getResponse() throws IOException {
        responseBuilder.addStatus(HttpStatus.OKAY.getResponseCode());
        responseBuilder.addContentType("text/html");
        responseBuilder.addBodyContents(reader, "/board.html");
        return responseBuilder.getResponse();
    }
}
