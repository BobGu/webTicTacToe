import configuration.Configuration;
import controllers.GameStatusController;
import controllers.PlayerVsPlayerController;
import gameStatus.TicTacToeGameStatus;
import readers.ResourceReader;
import responseBuilders.TicTacToeResponseHeaderBuilder;
import routers.ResourceRouter;
import routes.Router;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Router router = new ResourceRouter();
        Configuration config = new Configuration(router);
        config.addRoute("/player-vs-player", new PlayerVsPlayerController(new ResourceReader(), new TicTacToeResponseHeaderBuilder()));
        config.addRoute("/game-won", new GameStatusController(new TicTacToeGameStatus()));
        config.setRoutes();
        config.startServer();
    }
}
