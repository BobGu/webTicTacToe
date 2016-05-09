import configuration.Configuration;
import controllers.AssetController;
import controllers.PlayerVsPlayerController;
import readers.ResourceReader;
import routes.FileRouter;
import routes.Router;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Configuration config = new Configuration();
        config.addRoute("/player-vs-player", new PlayerVsPlayerController(new ResourceReader()));
        config.addRoute("/js/PlayerVsPlayer.js", new AssetController(new ResourceReader()));
        config.addRoute("/css/board.css", new AssetController(new ResourceReader()));
        Router router = new FileRouter();
        config.setRouter(router);
        config.setRoutes();
        config.startServer();
    }
}
