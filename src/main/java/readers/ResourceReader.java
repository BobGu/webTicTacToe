package readers;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResourceReader implements Reader{
    public byte[] read(String location) throws IOException {
        InputStream input = getClass().getResourceAsStream(location);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];

        while((nRead = input.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();

        return buffer.toByteArray();
    }

    public boolean canRead(String location) {
        InputStream input = getClass().getResourceAsStream(location);
        return input != null;
    }

}
