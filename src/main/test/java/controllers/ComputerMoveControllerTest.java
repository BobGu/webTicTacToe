import controllers.ComputerMoveController;
import converters.Converter;
import gameservice.GameService;
import httpStatus.HttpStatus;
import org.junit.Test;
import requests.Request;
import responseBuilders.ResponseBuilder;
import responseBuilders.json.JsonBuilder;
import responseBuilders.json.TicTacToeJsonBuilder;
import specialCharacters.EscapeCharacters;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class ComputerMoveControllerTest {
    private String board = "{\"board\": [\"0\", \"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\", \"8\"],"
            + "\"marker\": \"X\"}";
    private Request request = new Request("Full request", "/computer-move", "GET", board, null);
    private MockResponseBuilder builder = new MockResponseBuilder();
    private MockGameService service = new MockGameService();
    private MockConverter converter = new MockConverter();
    private MockJsonBuilder jsonBuilder = new MockJsonBuilder();
    private ComputerMoveController controller = new ComputerMoveController(service, builder, converter, jsonBuilder);

    @Test
    public void twoHundredOkayInResponse() throws IOException {
        byte[] response = controller.handle(request);
        String responseString = new String(response);

        assertTrue(responseString.contains(HttpStatus.OKAY.getResponseCode()));
    }

    @Test
    public void returnTheComputersMove() throws IOException {
        byte[] response = controller.handle(request);
        String responseString = new String(response);

        assertTrue(responseString.contains("{\"move\":\"2\"}"));
    }

    @Test
    public void jsonBuilderGetsCalled() throws IOException {
        controller.handle(request);
        assertTrue(jsonBuilder.getIsComputerMoveCalled());
    }

    private class MockResponseBuilder implements ResponseBuilder {
        public byte[] getResponse() {
            String responseHeaders = HttpStatus.OKAY.getResponseCode()
                    + EscapeCharacters.newline
                    + EscapeCharacters.newline
                    + "{\"move\":\"2\"}";
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
        private boolean isComputerMoveCalled = false;

        public boolean getIsComputerMoveCalled() {
            return isComputerMoveCalled;
        }

        public String computerMove(int move) {
            isComputerMoveCalled = true;
            return "2";
        }

        public String gameWon(boolean isGameWon) {
            return "Yes game is won";
        }

    }

}
