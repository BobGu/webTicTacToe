import httpStatus.HttpStatus;
import org.junit.Test;
import requests.Request;

import static org.junit.Assert.assertTrue;

public class GameModeController {
    private controllers.GameModeController controller = new controllers.GameModeController();
    private Request getRequest = new Request("full request", "/game-mode", "GET", null, null);

    @Test
    public void getsATwoHundredOkayResponse() {
        byte[] response = controller.handle(getRequest);
        String responseString = new String(response);

        assertTrue(responseString.contains(HttpStatus.OKAY.getResponseCode()));
    }

    @Test
    public void handlesAGetRequest() {
        byte[] response = controller.handle(getRequest);
        String responseString = new String(response);

        assertTrue(responseString.contains("Please choose a game mode"));
    }

    @Test
    public void getsTwoHundredOkayForAPost() {
        Request request = new Request("A request", "/game-mode", "POST", null, null);
        byte[] response = controller.handle(request);
        String responseString = new String(response);

        assertTrue(responseString.contains(HttpStatus.REDIRECT.getResponseCode()));
    }

    @Test
    public void handlesAPostRequest() {
        Request request = new Request("A request", "/game-mode", "POST", "hh", null);
        byte[] response = controller.handle(request);
        String responseString = new String(response);

        assertTrue(responseString.contains("Location: localhost:5000/first-player-name"));
    }

    @Test
    public void handlesInvalidGameMode() {
        Request request = new Request("A request", "/game-mode", "POST", "USERS* DROP TABLE", null);
        byte[] response = controller.handle(request);
        String responseString = new String(response);

        assertTrue(responseString.contains("Location: localhost:5000/game-mode"));
    }

}
