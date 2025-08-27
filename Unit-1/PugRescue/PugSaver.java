import java.util.ArrayList;
import java.util.Objects;

public class PugSaver {

	// Moves every dog whose breed is "Golden ____" in the list to the back of the list
	public static void rescueGolden(ArrayList<Dog> list) {
		int numGoldens = 0;
		for (int i = 0; i < list.size() - numGoldens; i++) {
			if (list.get(i).getBreed().length() >= 6
					&& list.get(i).getBreed().substring(0, 6) == "Golden") {
				list.set(i, list.set(list.size() - 1 - numGoldens, list.get(i)));
				numGoldens++;
			}
		}
	}
}
