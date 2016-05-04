import controllers.GameSetupController;
import httpStatus.HttpStatus;
import org.junit.Test;
import requests.Request;

import static org.junit.Assert.assertTrue;

public class GameSetupControllerTest {
    private GameSetupController controller = new GameSetupController();
    private Request getRequest = new Request("full request", "/game/setup", "GET", null, null);

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

}
