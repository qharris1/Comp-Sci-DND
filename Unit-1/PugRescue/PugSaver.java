public class PugSaver {

	// Moves every dog whose breed is "Pug" in the list to the back of the list
	// All non-pugs must remain in the same relative order they were in originally
	// and all pugs must also remain in the same relative order they were in originally
	public static void rescuePugs(MyArrayList<Dog> list) {
		int numGoldens = 0;
		for (int i = 0; i < list.size() - numGoldens - 1; i++) {
			if (list.get(i).getBreed().contains("Golden")) {
				for (int j = list.size() - 1 - numGoldens; j >= 0; j--) {
					if (!list.get(j).getBreed().contains("Golden")) {
						list.set(i, list.set(j, list.get(i)));
						numGoldens++;
						break;
					}
				}
			}
		}
	}
}
