package routers;

import controllers.Controller;
import httpStatus.HttpStatus;
import requests.Request;
import responseBuilders.ResponseBuilder;
import responseBuilders.TicTacToeResponseBuilder;
import routes.Route;
import routes.Router;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class ResourceRouter implements Router {
    private ArrayList<Route> routes;
    private ResponseBuilder responseBuilder = new TicTacToeResponseBuilder();
    private Controller assetController;

    public ResourceRouter(Controller controller) {
        this.assetController = controller;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public boolean pathExists(String path) {
        return routes.stream().anyMatch(pathExist(path));
    }

    public byte[] direct(Request request) throws IOException {
        String path = request.getPath();

        if (pathExists(path)) {
            Route route = getRoute(path);
            return route.getController().handle(request);
        } else if(isResourcePath(path)) {
            return assetController.handle(request);
        } else {
            responseBuilder.addStatus(HttpStatus.NOT_FOUND.getResponseCode());
            return responseBuilder.getResponse();
        }
    }

    public boolean isRoutes() {
        return !(routes == null);
    }

    private boolean isResourcePath(String path) {
        return path.contains(".html") || path.contains(".css") || path.contains(".js");
    }

    private Predicate<Route> pathExist(String path) {
        return route -> route.getPath().equals(path);
    }

    private Route getRoute(String path) {
        return routes.stream()
                .filter(route -> route.getPath().equals(path))
                .findFirst()
                .get();
    }

    public void setDirectoryLocation(String location) {

    }


}
