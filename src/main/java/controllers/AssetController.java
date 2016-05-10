package controllers;

import httpStatus.HttpStatus;
import readers.Reader;
import requests.Request;
import specialCharacters.EscapeCharacters;

import java.io.IOException;

public class AssetController implements Controller{
    private Reader reader;

    public AssetController(Reader reader) {
        this.reader = reader;
    }

    public byte[] handle(Request request) throws IOException {
        String responseHeader = HttpStatus.OKAY.getResponseCode() + EscapeCharacters.newline + EscapeCharacters.newline;
        byte[] fileContents = reader.read(request.getPath());
        String responseBody = new String(fileContents);
        String response = responseHeader + responseBody;
        return response.getBytes();
    }
}
