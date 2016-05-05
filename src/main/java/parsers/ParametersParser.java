package parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParametersParser {

    public String parseForValue(String keyAndValue, String key) {
        Pattern pattern = Pattern.compile(key + "=([^&]+)");
        Matcher matcher = pattern.matcher(keyAndValue);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }
}
