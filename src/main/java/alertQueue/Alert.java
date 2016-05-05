package alertQueue;

import java.util.LinkedList;
import java.util.Queue;

public class Alert{
    private static Alert alert = new Alert();
    private LinkedList<String> queue = new LinkedList<String>();

    private Alert (){};

    public static Alert getInstance() {
        return alert;
    }

    public void add(String message) {
        queue.add(message);
    }

    public String removeAndReturnFirst() {
        return queue.poll();
    }
}
