package responseBuilders;

import readers.Reader;

import java.io.IOException;

public interface ResponseBuilder {

    public byte[] getResponse();
    public void addStatus(String status1);
    public void addContentType(String contentType);
    public void addBodyContents(byte[] contents);
}
