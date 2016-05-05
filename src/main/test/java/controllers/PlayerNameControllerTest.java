import httpStatus.HttpStatus;
import org.junit.Test;
import requests.Request;
import controllers.PlayerNameController;
import httpStatus.HttpStatus;

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
}
