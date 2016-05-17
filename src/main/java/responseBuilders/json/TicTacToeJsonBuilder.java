package responseBuilders.json;

public class TicTacToeJsonBuilder {

    public String computerMove(int computerMove) {
        return "{\"computerMove:\"\"" + computerMove + "\"}";
    }
}
