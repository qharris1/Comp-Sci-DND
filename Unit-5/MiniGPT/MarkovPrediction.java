import java.util.ArrayList;
import java.util.HashMap;

public class MarkovPrediction {

    // Please define your own variables and data structures
    public HashMap<String, ArrayList<Character>> states = new HashMap<String, ArrayList<Character>>();

    public HashMap<String, ArrayList<Character>> readData(String str, char next) {
        if (states.get(str) == null) {
            states.put(str, new ArrayList<Character>());
        }
        states.get(str).add(next);
        return states;
    }

    // Method to predict the next state given a current state
    public Character predictNextState(String currentState) {
        try {
            states.get(currentState).get((int) (Math.random() * states.get(currentState).size()));
        } catch (Exception e){
            System.out.println(currentState + " =============================================================");
        }
        return states.get(currentState).get((int) (Math.random() * states.get(currentState).size()));
    }
}