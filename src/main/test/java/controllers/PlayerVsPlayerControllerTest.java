import controllers.PlayerVsPlayerController;
import httpStatus.HttpStatus;
import org.junit.Test;
import readers.FileReader;
import readers.Reader;
import requests.Request;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class PlayerVsPlayerControllerTest {
    private MockFileReader reader = new MockFileReader();
    private PlayerVsPlayerController controller = new PlayerVsPlayerController(reader);
    private Request getRequest = new Request("The Full Request", "/player-vs-player", "GET", null, null);

    public String responseToGetRequest() throws IOException {
        byte[] response = controller.handle(getRequest);
        return new String(response);
    }

    @Test
    public void twoHundredOkay() throws IOException {
        String response = responseToGetRequest();
        assertTrue(response.contains(HttpStatus.OKAY.getResponseCode()));
    }

    @Test
    public void contentTypeIsCorrect() throws IOException {
        String response = responseToGetRequest();
        assertTrue(response.contains("Content-Type: text/html"));
    }

    @Test
    public void responseBodyHasFileContents() throws IOException {
        String response = responseToGetRequest();
        assertTrue(response.contains("These are the file contents"));
    }

    private class MockFileReader implements Reader {
        public byte[] read(String location) {
            String fileContents = "These are the file contents";
            return fileContents.getBytes();
        }
    }
}
