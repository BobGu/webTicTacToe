import controllers.AssetController;
import controllers.Controller;
import httpStatus.HttpStatus;
import org.junit.Test;
import readers.Reader;
import requests.Request;
import responseBuilders.ResponseBuilder;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class AssetControllerTest {
    private MockReader reader = new MockReader(true);
    private MockResponseBuilder builder = new MockResponseBuilder();
    private Request request = new Request("full request", "/index.html", "GET", null, null);

    @Test
    public void getResponseIsInvoked() throws IOException {
        Controller controller = new AssetController(reader, builder);
        controller.handle(request);

        assertTrue(builder.getResponseInvoked());
    }

    @Test
    public void twoHundredOkayIfResourceExists() throws IOException {
        Controller controller = new AssetController(reader, builder);
        byte[] response = controller.handle(request);
        String responseString = new String(response);

        assertTrue(responseString.contains(HttpStatus.OKAY.getResponseCode())) ;
    }

    @Test
    public void fourOhFourIfResourceDoesNotExist() throws IOException {
        MockReader reader = new MockReader(false);
        Controller controller = new AssetController(reader, builder);
        byte[] response = controller.handle(request);
        String responseString = new String(response);

        assertTrue(responseString.contains(HttpStatus.NOT_FOUND.getResponseCode()));
    }

    @Test
    public void resourceIsReadIfItExits() throws IOException {
        Controller controller = new AssetController(reader, builder);
        controller.handle(request);

        assertTrue(reader.getIsRead());
    }

    @Test
    public void resourceIsNotReadIfDoesNotExist() throws IOException {
        MockReader reader = new MockReader(false);
        Controller controller = new AssetController(reader, builder);
        controller.handle(request);

        assertFalse(reader.getIsRead());
    }

    private class MockReader implements Reader {
        private boolean doesResourceExist;
        private boolean isRead = false;

        public MockReader(boolean doesResourceExist) {
            this.doesResourceExist = doesResourceExist;
        }

        public byte[] read(String location) {
            isRead = true;
            return new byte[4];
        }

        public boolean getIsRead() {
            return isRead;
        }

        public boolean canRead(String location) {
            return doesResourceExist;
        }
    }

    private class MockResponseBuilder implements ResponseBuilder {
        boolean responseInvoked = false;
        String response;

        public void addBodyContents(byte[] contents) {}
        public void addStatus(String status) {response += status;}
        public void addContentType(String type) {}
        public byte[] getResponse() {
            responseInvoked = true;
            return response.getBytes();
        }

        public boolean getResponseInvoked() {
            return responseInvoked;
        }

    }



}
