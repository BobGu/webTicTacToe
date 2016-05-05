import alertQueue.Alert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class AlertTest {
    private Alert alert = Alert.getInstance();

    @Test
    public void oneInstanceOfAlertQueue() {
        Alert sameAlert = Alert.getInstance();

        assertEquals(alert, sameAlert);
    }

    @Test
    public void addAnAlertToTheQueue() {
        alert.add("That is not a valid move");
        assertEquals("That is not a valid move", alert.removeAndReturnFirst());
    }

}
