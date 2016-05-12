import httpStatus.HttpStatus;
import org.junit.Test;
import responseBuilders.TicTacToeResponseHeaderBuilder;

import static org.junit.Assert.assertTrue;

public class TicTacToeResponseHeaderBuilderTest {
    private TicTacToeResponseHeaderBuilder builder = new TicTacToeResponseHeaderBuilder();

    @Test
    public void addsResponseCode() {
        builder.addStatus(HttpStatus.OKAY.getResponseCode());
        String response = builder.getResponseHeader();
        assertTrue(response.contains(HttpStatus.OKAY.getResponseCode()));
    }

    @Test
    public void addsContentType() {
        builder.addContentType("text/html");
        String response = builder.getResponseHeader();
        assertTrue(response.contains("Content-Type: text/html"));
    }

}
