package routers;

import controllers.AssetController;
import controllers.Controller;
import httpStatus.HttpStatus;
import readers.ResourceReader;
import requests.Request;
import routes.FileRouter;
import routes.Route;
import specialCharacters.EscapeCharacters;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Optional;

public class ResourceRouter extends FileRouter {
    private ArrayList<Route> routes;

   public void setRoutes(ArrayList<Route> routes) {
       this.routes = routes;
   }

    @Override
    public byte[] direct(Request request) throws IOException {
        byte[] response;
        Optional<Route> route = findRoute(request.getPath());
        String resourceLocation = request.getPath();
        InputStream input = getClass().getResourceAsStream(resourceLocation);
        boolean resourceExists = input != null;

        if (route.isPresent()) {
            response = route.get().getController().handle(request);
        } else if (resourceExists) {
            Controller controller = new AssetController(new ResourceReader());
            response = controller.handle(request);
        } else {
            String responseString = HttpStatus.NOT_FOUND.getResponseCode()+ EscapeCharacters.newline + EscapeCharacters.newline;
            response = responseString.getBytes();
        }
        return response;
    }

    private Optional<Route> findRoute(String path) {
        return routes.stream()
                .filter(route -> route.getPath().equals(path))
                .findFirst();
    }

}
