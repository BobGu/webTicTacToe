import configuration.Configuration;
import controllers.GameStatusController;
import controllers.PlayerVsPlayerController;
import gameStatus.TicTacToeGameStatus;
import readers.ResourceReader;
import routers.ResourceRouter;
import routes.Router;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Configuration config = new Configuration();
        config.addRoute("/player-vs-player", new PlayerVsPlayerController(new ResourceReader()));
        config.addRoute("/game-won", new GameStatusController(new TicTacToeGameStatus()));
        Router router = new ResourceRouter();
        config.setRouter(router);
        config.setRoutes();
        config.startServer();
    }
}
