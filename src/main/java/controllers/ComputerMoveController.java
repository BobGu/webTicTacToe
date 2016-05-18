package controllers;

import converters.Converter;
import gameservice.GameService;
import httpStatus.HttpStatus;
import requests.Request;
import responseBuilders.ResponseBuilder;
import java.io.IOException;
import java.util.HashMap;
import responseBuilders.json.JsonBuilder;

public class ComputerMoveController implements Controller {
    private GameService service;
    private ResponseBuilder builder;
    private Converter converter;
    private JsonBuilder jsonBuilder;

    public ComputerMoveController(GameService service, ResponseBuilder builder, Converter converter, JsonBuilder jsonBuilder) {
        this.service = service;
        this.builder = builder;
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
