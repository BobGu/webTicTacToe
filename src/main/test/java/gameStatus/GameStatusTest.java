import gameStatus.GameStatus;
import gameStatus.TicTacToeGameStatus;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameStatusTest {
    private GameStatus gameStatus = new TicTacToeGameStatus();

    @Test
    public void gameIsWon() {
        Object winningBoard = createWinningBoard();
        assertTrue(gameStatus.gameWon(winningBoard));
    }

    @Test
    public void gameIsNotWon() {
        Object nonWinningBoard = createNonWinningBoard();
        assertFalse(gameStatus.gameWon(nonWinningBoard));
    }

    public ArrayList<Object> createWinningBoard() {
        ArrayList<Object> board = new ArrayList<Object>();
        board.add("X");
        board.add("X");
        board.add("X");
        board.add("O");
        board.add("O");
        board.add(5);
        board.add(6);
        board.add(7);
        board.add(8);

        return board;
    }

    public ArrayList<Object> createNonWinningBoard() {
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

}
