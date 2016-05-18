import converters.Converter;
import converters.JsonConverter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class JsonConverterTest {
    private Converter converter = new JsonConverter();

    @Test
    public void canConvertSimpleJson() {
        String json = "{\"name\":\"bob\"}";
        HashMap<String, Object> convertedJson = converter.toHashMap(json);

        assertEquals("bob", convertedJson.get("name"));
    }

    @Test
    public void canConvertJsonArrays() {
        String json = "{\"cities\":[\"Harrisonburg\", \"Los Angeles\"]}";
        HashMap<String, Object> convertedJson = converter.toHashMap(json);

        ArrayList<Object> cities = new ArrayList<Object>();
        cities.add("Harrisonburg");
        cities.add("Los Angeles");

        assertEquals(cities, convertedJson.get("cities"));
    }
}
