import configuration.Configuration;
import controllers.ComputerMoveController;
import controllers.GameStatusController;
import controllers.PlayerVsPlayerController;
import converters.JsonConverter;
import gameStatus.TicTacToeGameStatus;
import gameservice.TicTacToeService;
import readers.ResourceReader;
import responseBuilders.TicTacToeResponseBuilder;
import responseBuilders.json.TicTacToeJsonBuilder;
import routers.ResourceRouter;
import routes.Router;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Router router = new ResourceRouter();
        Configuration config = new Configuration(router);
        config.addRoute("/player-vs-player", new PlayerVsPlayerController(new ResourceReader(), new TicTacToeResponseBuilder()));
        config.addRoute("/game-won", new GameStatusController(new TicTacToeGameStatus(), new TicTacToeResponseBuilder(), new JsonConverter(), new TicTacToeJsonBuilder()));
        config.addRoute("/computer-move", new ComputerMoveController(new TicTacToeService(), new TicTacToeResponseBuilder(), new JsonConverter(), new TicTacToeJsonBuilder()));
        config.setRoutes();
        config.startServer();
    }
}
