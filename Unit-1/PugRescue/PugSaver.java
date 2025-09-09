import java.util.ArrayList;

public class PugSaver {

	// Moves every dog whose breed is "Pug" in the list to the back of the list
	// All non-pugs must remain in the same relative order they were in originally
	// and all pugs must also remain in the same relative order they were in originally
	public static void rescuePugs(ArrayList<Dog> list) {
		int numGoldens = list.size() - 1;
		for (int i = 0; i < numGoldens; i++) {
			if (isGolden(list.get(i))) {
				for (int j = numGoldens; j >= i; j--) {
					if (!isGolden(list.get(j))) {
						list.set(i, list.set(j, list.get(i)));
						numGoldens = j;
						break;
					}
				}
			}
		}
	}

	private static boolean isGolden(Dog dog){
		return dog.getBreed().contains("Golden");
	}
}
