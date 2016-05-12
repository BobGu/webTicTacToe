package gameStatus;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

import java.util.ArrayList;

public class TicTacToeGameStatus implements GameStatus{

    public boolean gameWon(ArrayList<Object> board) {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("ttt-clojure.rules"));
        IFn into = Clojure.var("clojure.core", "into");
        IFn createVector = Clojure.var("clojure.core", "vector");
        IFn gameWon = Clojure.var("ttt-clojure.rules", "game-won?");

        if (gameWon.invoke(into.invoke(createVector.invoke(), board)) == null) {
            return false;
        } else {
            return true;
        }
    }
}
