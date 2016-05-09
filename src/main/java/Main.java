import configuration.Configuration;
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
        Router router = new FileRouter();
        config.setRouter(router);
        config.setRoutes();
        config.startServer();
    }
}
