package readers;

import java.io.IOException;

public interface Reader {

    public byte[] read(String location) throws IOException;
    public boolean canRead(String location);

}
