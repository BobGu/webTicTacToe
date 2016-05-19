package controllers;

import httpStatus.HttpStatus;
import readers.Reader;
import requests.Request;
import responseBuilders.ResponseBuilder;
import specialCharacters.EscapeCharacters;

import java.io.IOException;
import java.io.InputStream;

public class AssetController implements Controller {
    private Reader reader;
    private ResponseBuilder builder;

    public AssetController(Reader reader, ResponseBuilder builder) {
        this.reader = reader;
        this.builder = builder;
    }

    public byte[] handle(Request request) throws IOException {
        InputStream input = getClass().getResourceAsStream(request.getPath());
        boolean resourceExists = input != null;

        if (resourceExists) {
            builder.addStatus(HttpStatus.OKAY.getResponseCode());
            byte[] fileContents = reader.read(request.getPath());
            builder.addBodyContents(fileContents);
        } else {
            builder.addStatus(HttpStatus.NOT_FOUND.getResponseCode());
        }
        return builder.getResponse();
    }
}
