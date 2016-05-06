import httpStatus.HttpStatus;
import org.junit.Test;
import requests.Request;
import controllers.PlayerNameController;

import static org.junit.Assert.assertTrue;

public class PlayerNameControllerTest {
    private Request request = new Request("A request", "/first-player-name", "GET", null, null);
    private PlayerNameController controller = new PlayerNameController();

    @Test
    public void twoHundredOkayForGetRequest() {
        byte[] response = controller.handle(request);
        String responseString = new String(response);

        assertTrue(responseString.contains(HttpStatus.OKAY.getResponseCode()));
    }

    @Test
    public void asksPlayerForTheirName() {
        byte[] response = controller.handle(request);
        String responseString = new String(response);

        assertTrue(responseString.contains("What is your name?"));
    }

    @Test
    public void postRespondsWithThreeOhTwoRedirect () {
        Request request = new Request("a request", "/first-player-name", "POST", "playerName=robert", null);
        byte[] response = controller.handle(request);
        String responseString = new String(response);

        assertTrue(responseString.contains(HttpStatus.REDIRECT.getResponseCode()));
    }

    @Test
    public void handlesPostWithInvalidName() {
        Request request = new Request("a request", "/first-player-name", "POST", "playerName=", null);
        byte[] response = controller.handle(request);
        String responseString = new String(response);

        assertTrue(responseString.contains("Location: http://localhost:5000/first-player-name"));
    }

    @Test
    public void handlesPostWithValidName() {
        Request request = new Request("a request", "/first-player-name", "POST", "playerName=bob", null);
        byte[] response = controller.handle(request);
        String responseString = new String(response);

        assertTrue(responseString.contains("Location: http://localhost:5000/first-player-piece"));
    }
}
