import controllers.GameStatusController;
import gameStatus.TicTacToeGameStatus;
import httpStatus.HttpStatus;
import org.junit.Test;
import requests.Request;
import responseBuilders.ResponseBuilder;
import responseBuilders.TicTacToeResponseBuilder;
import specialCharacters.EscapeCharacters;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class GameStatusControllerTest {

    @Test
    public void twoHundredOkayForPost() throws IOException {
        String board = "{\"board\": [\"0\", \"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\", \"8\"]}";
        Request request = new Request("The full request", "/game-status", "POST", board, null);
        GameStatusController controller = new GameStatusController(new TicTacToeGameStatus(), new MockResponseBuilder());
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

}