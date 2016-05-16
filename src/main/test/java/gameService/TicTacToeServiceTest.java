import gameservice.TicTacToeService;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class TicTacToeServiceTest {

    public ArrayList<Object> createBoard() {
        ArrayList<Object> board = new ArrayList<Object>();
        board.add("X");
        board.add("X");
        board.add(2);
        board.add("O");
        board.add("O");
        board.add(5);
        board.add(6);
        board.add(7);
        board.add(8);

        return board;
    }

    @Test
    public void returnsAComputerMove() {
        ArrayList<Object> board = createBoard();
        TicTacToeService ticTacToeApp = new TicTacToeService();
        int computerMove = ticTacToeApp.computerMove(board, "X");
        assertEquals(2, computerMove);
    }
}
