
import controllers.Controller;
import httpStatus.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import requests.Request;
import routers.ResourceRouter;
import routes.Route;
import routes.Router;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class ResourceRouterTest {
    private MockAssetController assetController = new MockAssetController();
    private ResourceRouter router = new ResourceRouter(assetController);
    private MockController controller = new MockController();

    @Before
    public void setup() {
        ArrayList<Route> routes = new ArrayList<Route>();
        Route indexRoute = new Route("/", controller);
        routes.add(indexRoute);
        router.setRoutes(routes);
    }

    @Test
    public void returnsTrueIfPathExists() {
        assertTrue(router.pathExists("/"));
    }

    @Test
    public void returnsFalseIfPathDoesNotExist() {
        assertFalse(router.pathExists("/foobar"));
    }

    @Test
    public void thereAreRoutesIfRoutesSet() {
        assertTrue(router.isRoutes());
    }


    @Test
    public void thereAreNoRoutesIfNoneAreSet() {
        Router router = new ResourceRouter(assetController);
        assertFalse(router.isRoutes());
    }

    @Test
    public void fourOhFourIfPathDoesNotExist() throws IOException {
        Request request = new Request("The Request", "/foobar", "GET", null, null);
        byte[] response = router.direct(request);
        String responseString = new String(response);

        assertTrue(responseString.contains(HttpStatus.NOT_FOUND.getResponseCode()));
    }

    @Test
    public void callsControllerHandleIfPathExists() throws IOException {
        Request request = new Request("The Full request", "/", "GET", null, null);
        router.direct(request);

        assertTrue(controller.getIsHandleInvoked());
    }

    @Test
    public void callsCorrectControllerHandleIfResourceRequested() throws IOException {
        Request request = new Request("A request", "/index.css", "GET", null, null);
        router.direct(request);

        assertTrue(assetController.getIsHandleInvoked());
    }

    private class MockController implements Controller {
        private boolean isHandleInvoked = false;

        public byte[] handle(Request request) {
            isHandleInvoked = true;
            return "These are the contents".getBytes();
        }

        public boolean getIsHandleInvoked() {
            return isHandleInvoked;
        }

    }

    private class MockAssetController extends MockController {

    }

}
