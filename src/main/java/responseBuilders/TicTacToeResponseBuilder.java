package responseBuilders;

import gameservice.TicTacToeService;
import readers.Reader;
import responseBuilders.json.JsonBuilder;
import specialCharacters.EscapeCharacters;

import java.io.IOException;

public class TicTacToeResponseBuilder implements ResponseBuilder {
    private String responseHeaders;
    private byte[] responseBody;

    public byte[] getResponse() {
        return addHeadersAndBody();
    }

    public void addStatus(String status) {
        responseHeaders += status + EscapeCharacters.newline;
    }

    public void addContentType(String contentType) {
        responseHeaders += "Content-Type: " + contentType + EscapeCharacters.newline;
    }

    public void addBodyContents(byte[] contents) {
        responseBody = contents;
    }

    private byte[] getResponseHeaders() {
        String headers = responseHeaders + EscapeCharacters.newline;
        return headers.getBytes();
    }

    private byte[] addHeadersAndBody() {
        byte[] headers = getResponseHeaders();
        if (isResponseBody()) {
            byte[] response = new byte[headers.length + responseBody.length];

            System.arraycopy(headers, 0, response, 0, headers.length);
            System.arraycopy(responseBody, 0, response, headers.length, responseBody.length);
            return response;
        } else {
            return headers;
        }
    }

    private boolean isResponseBody() {
        return responseBody != null;
    }
}
