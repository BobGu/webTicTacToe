package parameters;

import java.util.ArrayList;

public class Parameters {
    private ArrayList<Object> board;
    private String marker;

    public void setBoard(ArrayList<Object> board) {
        this.board = board;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public ArrayList<Object> getBoard() {
        return board;
    }

    public String getMarker() {
        return marker;
    }
}

