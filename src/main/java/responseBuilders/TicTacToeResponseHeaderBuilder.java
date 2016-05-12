package responseBuilders;

import specialCharacters.EscapeCharacters;

public class TicTacToeResponseHeaderBuilder implements ResponseHeaderBuilder {
    private String response;

    public String getResponseHeader()  {
        return response + EscapeCharacters.newline;
    }

    public void addStatus(String status) {
        response += status + EscapeCharacters.newline;
    }

    public void addContentType(String contentType) {
        response += "Content-Type: " + contentType + EscapeCharacters.newline;
    }
}
