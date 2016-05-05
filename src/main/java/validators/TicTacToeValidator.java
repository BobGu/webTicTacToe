package validators;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

public class TicTacToeValidator {

    public boolean validGameMode(String input) {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("ttt-clojure.validate-input"));
        IFn gameMode = Clojure.var("ttt-clojure.validate-input", "valid-game-mode?");
        boolean message = (boolean) gameMode.invoke(input);
        return message;
    }
}
