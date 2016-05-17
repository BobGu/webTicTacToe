package responseBuilders.json;

import converters.JsonConverter;

public class TicTacToeJsonBuilder implements JsonBuilder {

    public String computerMove(int computerMove) {
        return "{\"computerMove:\"\"" + computerMove + "\"}";
    }

    public String gameWon(boolean isGameWon) {
        return  "{\"response\":{\"gameStatus\":"  +
                "{\"gameWon\":\"" + isGameWon + "\"}}}";
    }
}
