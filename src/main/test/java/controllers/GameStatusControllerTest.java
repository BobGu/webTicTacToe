import controllers.GameStatusController;
import gameStatus.TicTacToeGameStatus;
import org.junit.Test;
import requests.Request;
import responseBuilders.TicTacToeResponseBuilder;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class GameStatusControllerTest {

    @Test
    public void twoHundredOkayForPost() throws IOException {
        String board = "{\"board\": [\"0\", \"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\", \"8\"]}";
        Request request = new Request("The full request", "/game-status", "POST", board, null);
        GameStatusController controller = new GameStatusController(new TicTacToeGameStatus(), new TicTacToeResponseBuilder());
        byte[] response = controller.handle(request);
        String responseString = new String(response);
        String expected = "{\"response\":{\"gameStatus\":{\"gameWon\":\"false\"}}}";

        assertTrue(responseString.contains(expected));
    }

}