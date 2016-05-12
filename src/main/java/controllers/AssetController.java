package controllers;

import httpStatus.HttpStatus;
import readers.Reader;
import requests.Request;
import responseBuilders.ResponseBuilder;
import specialCharacters.EscapeCharacters;

import java.io.IOException;

public class AssetController implements Controller{
    private Reader reader;
    private ResponseBuilder builder;

    public AssetController(Reader reader, ResponseBuilder builder) {
        this.reader = reader;
        this.builder = builder;
    }

    public byte[] handle(Request request) throws IOException {
        builder.addStatus(HttpStatus.OKAY.getResponseCode());
        builder.addBodyContents(reader, request.getPath());
        return builder.getResponse();
    }
}
