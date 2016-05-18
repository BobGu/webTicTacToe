package converters;

import java.util.HashMap;

public interface Converter {

    HashMap<String, Object> toHashMap(String body);

}

