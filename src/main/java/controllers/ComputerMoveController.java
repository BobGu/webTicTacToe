package controllers;

import gameservice.GameService;
import parameters.Parameters;
import httpStatus.HttpStatus;
import requests.Request;
import responseBuilders.ResponseBuilder;
import java.io.IOException;
import java.util.HashMap;

import converters.JsonConverter;

public class ComputerMoveController {
    private ResponseBuilder builder;
    private GameService service;
    private JsonConverter jsonConverter = new JsonConverter();

    public ComputerMoveController(ResponseBuilder builder, GameService service) {
        this.builder = builder;
        this.service = service;
    }

    public byte[] handle(Request request) throws IOException {
        return getComputerMove(request.getParameters());
    }

    private byte[] getComputerMove(String body) throws IOException {
        HashMap<String, Object> convertedJson = jsonConverter.toHashMap(body);
        int computerMove = service.computerMove(convertedJson.get("board"), convertedJson.get("marker"));
        String move = String.valueOf(computerMove);
        builder.addStatus(HttpStatus.OKAY.getResponseCode());
        builder.addBodyContents(move.getBytes());
        return builder.getResponse();
    }

}
