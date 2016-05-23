import controllers.PlayerVsPlayerController;
import httpStatus.HttpStatus;
import org.junit.Test;
import readers.Reader;
import requests.Request;
import responseBuilders.ResponseBuilder;
import specialCharacters.EscapeCharacters;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class PlayerVsPlayerControllerTest {
    private MockFileReader reader = new MockFileReader();
    private MockResponseBuilder headerBuilder = new MockResponseBuilder();
    private PlayerVsPlayerController controller = new PlayerVsPlayerController(reader, headerBuilder);
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

        public boolean canRead(String location) {return true;}

    }

    private class MockResponseBuilder implements ResponseBuilder {
        public byte[] getResponse() {
            String responseHeaders = HttpStatus.OKAY.getResponseCode()
                                   + EscapeCharacters.newline
                                   + "Content-Type: text/html"
                                   + EscapeCharacters.newline
                                   + EscapeCharacters.newline
                                   + "These are the file contents";
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
