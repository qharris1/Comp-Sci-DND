import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MiniGPT {

	MarkovPrediction predictor;

	public MiniGPT(String fileName, int chainOrder) {
		predictor = new MarkovPrediction();
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			int charAsInt;
			StringBuilder input = new StringBuilder();
			// Read until the end of the stream (-1 is returned)
			while ((charAsInt = reader.read()) != -1) {
				// Cast the integer value to a character
				char character = (char) charAsInt;
				if (input.length() == chainOrder) {
					predictor.readData(input.toString(), character);
					input.deleteCharAt(0);
				}
				input.append(character);
			}
		} catch (IOException e) {
			System.err.println("An I/O error occurred: " + e.getMessage());
		}
	}

	public void generateText(String outputFileName, int numChars) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
			int generated = 0;
			StringBuilder last = new StringBuilder();
			while (generated < numChars) {
				if (generated == 0) {
					last.append(predictor.states.keySet().toArray()[(int) (Math.random() * predictor.states.size())]
							.toString());
					writer.write(last.toString());
					generated += last.length();
				} else {
					char c = predictNextState(last.toString());
					writer.write(c);
					last.append(c).deleteCharAt(0);
					generated++;
				}
			}
			writer.close();
		} catch (IOException e) {
			System.err.println("An I/O error occurred: " + e.getMessage());
		}
	}

	// Method to predict the next state given a current state
	public Character predictNextState(String currentState) {
		return predictor.predictNextState(currentState);
	}
}
