package controllers;

import converters.Converter;
import gameservice.GameService;
import httpStatus.HttpStatus;
import requests.Request;
import responseBuilders.ResponseBuilder;
import java.io.IOException;
import java.util.HashMap;
import responseBuilders.json.JsonBuilder;

public class ComputerMoveController {
    private ResponseBuilder builder;
    private GameService service;
    private Converter converter;
    private JsonBuilder jsonBuilder;

    public ComputerMoveController(ResponseBuilder builder, GameService service, Converter converter, JsonBuilder jsonBuilder) {
        this.builder = builder;
        this.service = service;
        this.converter = converter;
        this.jsonBuilder = jsonBuilder;
    }

    public byte[] handle(Request request) throws IOException {
        return getComputerMove(request.getParameters());
    }

    private byte[] getComputerMove(String body) throws IOException {
        HashMap<String, Object> convertedJson = converter.toHashMap(body);
        int move = service.computerMove(convertedJson.get("board"), convertedJson.get("marker"));
        String json = (String) jsonBuilder.computerMove(move);
        builder.addStatus(HttpStatus.OKAY.getResponseCode());
        builder.addBodyContents(json.getBytes());
        return builder.getResponse();
    }

}
