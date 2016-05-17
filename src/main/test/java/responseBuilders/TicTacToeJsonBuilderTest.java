import org.junit.Test;
import responseBuilders.json.TicTacToeJsonBuilder;

import static junit.framework.TestCase.assertEquals;

public class TicTacToeJsonBuilderTest {

    @Test
    public void computerMoveJson() {
        TicTacToeJsonBuilder jsonBuilder = new TicTacToeJsonBuilder();
        assertEquals("{\"computerMove:\"\"2\"}", jsonBuilder.computerMove(2));
    }

}
