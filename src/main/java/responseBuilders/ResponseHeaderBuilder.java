package responseBuilders;

public interface ResponseHeaderBuilder {

    public String getResponseHeader();
    public void addStatus(String status1);
    public void addContentType(String contentType);
}
