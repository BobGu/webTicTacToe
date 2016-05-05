import configuration.Configuration;
import controllers.GameModeController;
import routes.FileRouter;
import routes.Router;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Configuration config = new Configuration();
        config.addRoute("/game-mode", new GameModeController());
        Router router = new FileRouter();
        config.setRouter(router);
        config.setRoutes();
        config.startServer();
    }
}
