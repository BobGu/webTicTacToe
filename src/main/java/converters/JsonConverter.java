package converters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class JsonConverter implements Converter {

    public HashMap<String, Object> toHashMap(String json) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        JSONObject jObject = new JSONObject(json);
        Iterator<String> keys = jObject.keys();

        while( keys.hasNext() ){
            String key = (String)keys.next();
            Object value = jObject.get(key);

            if(value instanceof JSONArray) {
                ArrayList<Object> board = toList((JSONArray) value);
                ArrayList newBoard = stringSpacesToIntegerSpaces(board);
                map.put(key, newBoard);
            } else {
                map.put(key, value.toString());
            }
        }
        return map;
    }

    private ArrayList<Object> toList(JSONArray jsonArray) throws JSONException {
        ArrayList<Object> list = new ArrayList<Object>();
        for(int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }

    private ArrayList<Object> stringSpacesToIntegerSpaces(ArrayList<Object> board) {
        ArrayList<Object> newBoard = new ArrayList<Object>();

        for(Object space : board) {
            if (tryParseInt(space)) {
                newBoard.add(Integer.parseInt(space.toString()));
            } else {
                newBoard.add(space);
            }
        }
        return newBoard;
    }

    boolean tryParseInt(Object value) {
        try {
            Integer.parseInt(value.toString());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
