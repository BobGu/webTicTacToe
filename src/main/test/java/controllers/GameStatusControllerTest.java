import controllers.GameStatusController;
import converters.Converter;
import gameStatus.GameStatus;
import gameStatus.TicTacToeGameStatus;
import gameservice.GameService;
import httpStatus.HttpStatus;
import org.junit.Test;
import requests.Request;
import responseBuilders.ResponseBuilder;
import responseBuilders.json.JsonBuilder;
import specialCharacters.EscapeCharacters;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class GameStatusControllerTest {
    private String board  = "{\"board\": [\"0\", \"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\", \"8\"]}";
    private Request request = new Request("The full request", "/game-status", "POST", board, null);
    private GameStatus gameStatus = new MockGameStatus();
    private ResponseBuilder responseBuilder = new MockResponseBuilder();
    private Converter converter = new MockConverter();
    private JsonBuilder jsonBuilder = new MockJsonBuilder();
    private GameStatusController controller = new GameStatusController(gameStatus, responseBuilder, converter, jsonBuilder);

    @Test
    public void gameWonResponseIsFalse() throws IOException {
        byte[] response = controller.handle(request);
        String responseString = new String(response);
        String expected = "{\"response\":{\"gameStatus\":{\"gameWon\":\"false\"}}}";

        assertTrue(responseString.contains(expected));
    }

    private class MockResponseBuilder implements ResponseBuilder {
        public byte[] getResponse() {
            String responseHeaders = HttpStatus.OKAY.getResponseCode()
                    + EscapeCharacters.newline
                    + "Content-Type: text/html"
                    + EscapeCharacters.newline
                    + EscapeCharacters.newline
                    + "{\"response\":{\"gameStatus\":{\"gameWon\":\"false\"}}}";
            return responseHeaders.getBytes();
        }

        public void addStatus(String status) {

        }

        public void addContentType(String contentType) {

        }

        public void addBodyContents(byte[] contents) {

        }

    }

    private class MockGameService implements GameService {

        public Integer computerMove(Object board, Object marker) {
            return 2;
        }
    }

    private class MockConverter implements Converter {

        public HashMap<String, Object> toHashMap(String json) {
            return new HashMap<String,Object>();
        }
    }

    private class MockJsonBuilder implements JsonBuilder {
        public String computerMove(int computerMove){return "Computer move";}
        public String gameWon(boolean isGameWon){return "Winner";}
    }

    private class MockGameStatus implements GameStatus {
        public boolean gameWon(Object board) {
            return true;
        }
    }

}