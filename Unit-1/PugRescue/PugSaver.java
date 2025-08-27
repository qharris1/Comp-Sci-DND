import java.util.ArrayList;
import java.util.Objects;

public class PugSaver {

	// Moves every dog whose breed is "Golden ____" in the list to the back of the list
	public static void rescueGolden(ArrayList<Dog> list) {
		ArrayList<Dog> goldens = new ArrayList<>();
		ArrayList<Dog> undesireables = new ArrayList<>();
		for (int i = 0; i < list.size(); i++){
			list.get(i).getName().substring(0, 6).equals("Golden") ? goldens.add(0, list.get(i)) : undesireables.add(0, list.get(i));
		}
	}
}
