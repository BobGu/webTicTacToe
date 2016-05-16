package gameservice;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

import java.util.ArrayList;

public class TicTacToeService implements GameService {

    public Integer computerMove(ArrayList<Object> board, String marker) {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("ttt-clojure.minimax"));

        IFn into = Clojure.var("clojure.core", "into");
        IFn createVector = Clojure.var("clojure.core", "vector");
        IFn move = Clojure.var("ttt-clojure.minimax", "better-move");

        int computersMove = (int) move.invoke(marker, into.invoke(createVector.invoke(), board));
        return computersMove;
    }
}
