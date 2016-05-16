import configuration.Configuration;
import controllers.GameStatusController;
import controllers.PlayerVsPlayerController;
import gameStatus.TicTacToeGameStatus;
import readers.ResourceReader;
import responseBuilders.TicTacToeResponseBuilder;
import routers.ResourceRouter;
import routes.Router;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Router router = new ResourceRouter();
        Configuration config = new Configuration(router);
        config.addRoute("/player-vs-player", new PlayerVsPlayerController(new ResourceReader(), new TicTacToeResponseBuilder()));
        config.addRoute("/game-won", new GameStatusController(new TicTacToeGameStatus(), new TicTacToeResponseBuilder()));
        config.setRoutes();
        config.startServer();
    }
}
