package responseBuilders.json;

import java.util.ArrayList;

public interface JsonBuilder {
    public Object computerMove(int move);
    public Object gameWon(boolean isGameWon);

}
