package gameStatus;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

import java.util.ArrayList;

public class TicTacToeGameStatus implements GameStatus{

    public boolean gameWon(ArrayList<String> board) {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("ttt-clojure.rules"));
        IFn gameWon = Clojure.var("ttt-clojure.rules", "game-won?");
        boolean isGameWon = (boolean) gameWon.invoke(board);
        return isGameWon;
    }
}
