import httpStatus.HttpStatus;
import org.junit.Test;
import readers.Reader;
import responseBuilders.TicTacToeResponseBuilder;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class TicTacToeResponseBuilderTest {
    private MockFileReader reader = new MockFileReader();
    private TicTacToeResponseBuilder builder = new TicTacToeResponseBuilder();

    @Test
    public void addsResponseCode() {
        builder.addStatus(HttpStatus.OKAY.getResponseCode());
        byte[] response = builder.getResponse();
        String responseString = new String(response);
        assertTrue(responseString.contains(HttpStatus.OKAY.getResponseCode()));
    }

    @Test
    public void addsContentType() {
        builder.addContentType("text/html");
        byte[] response = builder.getResponse();
        String responseString = new String(response);
        assertTrue(responseString.contains("Content-Type: text/html"));
    }

    @Test
    public void addsResponseBody() throws IOException {
        byte[] fileContents = reader.read("file.html");
        builder.addBodyContents(fileContents);
        byte[] response = builder.getResponse();
        String responseString = new String(response);
        assertTrue(responseString.contains("These are the file contents"));
    }

    private class MockFileReader implements Reader {

        public byte[] read(String location) {
            String fileContents = "These are the file contents";
            return fileContents.getBytes();
        }

        public boolean canRead(String location) {return true;}
    }
}
