import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MiniGPT {

	private MarkovPrediction predictor;

	public MiniGPT(String fileName, int sequenceLength) {
		predictor = new MarkovPrediction();

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			int charAsInt;
			StringBuilder input = new StringBuilder();
			while ((charAsInt = reader.read()) != -1) {
				char character = (char) charAsInt;
				if (input.length() == sequenceLength) {
					predictor.addData(input.toString(), character);
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
					last.append(predictor.getRandomKey());
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

	public char predictNextState(String currentState) {
		return predictor.predictNextState(currentState);
	}
}
