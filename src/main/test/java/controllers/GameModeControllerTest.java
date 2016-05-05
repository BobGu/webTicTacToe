import alertQueue.Alert;
import httpStatus.HttpStatus;
import org.junit.After;
import org.junit.Test;
import requests.Request;

import static org.junit.Assert.assertTrue;

public class GameModeControllerTest {
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
    public void getsThreeOhTwoRedirect() {
        Request request = new Request("A request", "/game-mode", "POST", "hh", null);
        byte[] response = controller.handle(request);
        String responseString = new String(response);

        assertTrue(responseString.contains(HttpStatus.REDIRECT.getResponseCode()));
    }

    @Test
    public void handlesAPostRequest() {
        Request request = new Request("A request", "/game-mode", "POST", "gamemode=hh", null);
        byte[] response = controller.handle(request);
        String responseString = new String(response);

        assertTrue(responseString.contains("Location: http://localhost:5000/first-player-name"));
    }

    @Test
    public void handlesInvalidGameMode() {
        Request request = new Request("A request", "/game-mode", "POST", "gamemode=USERS* DROP TABLE", null);
        byte[] response = controller.handle(request);
        String responseString = new String(response);

        byte[] getResponse = controller.handle(getRequest);
        String getResponseString = new String(getResponse);

        assertTrue(responseString.contains("Location: http://localhost:5000/game-mode"));
        assertTrue(getResponseString.contains("USERS* DROP TABLE is not a valid game mode"));
    }


    @After
    public void clearQueue() {
        Alert alert = Alert.getInstance();
        while (alert.removeAndReturnFirst() != null) {
            alert.removeAndReturnFirst();
        }
    }


}
