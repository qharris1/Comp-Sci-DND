import java.util.ArrayList;
import java.util.HashMap;

public class MarkovPrediction {

    private HashMap<String, ArrayList<Character>> states =
            new HashMap<String, ArrayList<Character>>();

    public HashMap<String, ArrayList<Character>> addData(String str, char next) {
        if (states.get(str) == null) {
            states.put(str, new ArrayList<Character>());
        }
        states.get(str).add(next);
        return states;
    }

    public Character predictNextState(String currentState) {
        if (!states.containsKey(currentState)) {
            return states.get(getRandomKey()).get(0);
        }
        return states.get(currentState)
                .get((int) (Math.random() * states.get(currentState).size()));
    }

    public String getRandomKey() {
        return states.keySet().toArray()[(int) (Math.random() * states.size())].toString();
    }
}
