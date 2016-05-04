package messageFactory;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

public class TicTacToeMessageFactory implements MessageFactory {

    public String gameMode() {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("ttt-clojure.message-factory"));
        IFn gameMode = Clojure.var("ttt-clojure.message-factory", "game-mode");
        IFn deref = Clojure.var("clojure.core", "deref");
        String message = (String) deref.invoke(gameMode);
        return message;
    }
}
