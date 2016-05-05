import org.junit.Test;
import parsers.ParametersParser;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by robertgu on 5/5/16.
 */
public class ParameterParserTest {

    @Test
    public void canParseForValueGivenAKey() {
        String keyAndValue = "gamemode=hh";
        ParametersParser parser = new ParametersParser();
        assertEquals("hh", parser.parseForValue(keyAndValue, "gamemode"));
    }
}
