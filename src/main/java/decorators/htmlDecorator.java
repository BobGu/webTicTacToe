package decorators;

import alertQueue.Alert;

public class htmlDecorator {
    private static Alert alert = Alert.getInstance();

    public static String gameModeDecorate(String message) {
        String alertMessage = showAlert();
        String html = "<!DOCTYPE html>"
                + "<html><head></head><body><p>"
                + message
                + "</p><form action=\"http://localhost:5000/game-mode\" method=\"post\"><input name=\"gamemode\" type=\"radio\" value=\"hh\">Human vs Human</input>"
                + "<input name=\"gamemode\"type=\"radio\" value=\"hc\"> Human vs Computer</input>"
                + "<input type=\"submit\"/></form>"
                + alertMessage
                + "</body></html>";
        return html;
    }

    private static String showAlert() {
        String messageAlert = alert.removeAndReturnFirst();
        String html = "";
        if (messageAlert != null) {
            html += "<div class=\"alert\">"
                    + messageAlert
                    + "</div>";
        }

        return html;
    }
}
