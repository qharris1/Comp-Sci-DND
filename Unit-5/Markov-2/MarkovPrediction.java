import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MarkovPrediction {

    // Please define your own variables and data structures
    private HashMap<String, ArrayList<String>> states = new HashMap<String, ArrayList<String>>();

    public HashMap<String, ArrayList<String>> readData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (states.get(parts[0]) == null) {
                    states.put(parts[0], new ArrayList<String>());
                }
                states.get(parts[0]).add(parts[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return states;
    }

    // Method to predict the next state given a current state
    public String predictNextState(String currentState) {
        return states.get(currentState).get((int) (Math.random() * states.get(currentState).size()));
    }
}