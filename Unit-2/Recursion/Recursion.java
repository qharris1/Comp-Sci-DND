import java.util.ArrayList;

public class Recursion {

	// Prints the value of every node in the singly linked list with the given head,
	// but in reverse
	public static void printListInReverse(ListNode head) {
		if (head == null) {
			throw new IllegalArgumentException("Head is null");
		}
		if (head.getNext() == null) {
			System.out.println(head.getValue());
			return;
		}
		printListInReverse(head.getNext());
		System.out.println(head.getValue());
	}

	// For the given 2D array of Strings, replaces the String at index[r][c]
	// with "infected" unless the String is "vaccinated";
	// also, any Strings they are orthogonally adjacent to
	// that are not "vaccinated" will also be infected, and any adjacent to
	// them as well etc.
	// Infecting someone who is already infected has no effect
	// Trying to infect outside the confines of the grid also has no effect
	// Precondition: grid has no null entries
	public static void infect(String[][] grid, int r, int c) {
		if (grid == null) {
			throw new IllegalArgumentException("Grid is null");
		}
		if (r >= grid.length || r < 0) {
			return;
		}
		if (c >= grid[r].length || c < 0) {
			return;
		}
		if (grid[r][c] == "infected" || grid[r][c] == "vaccinated") {
			return;
		} else {
			grid[r][c] = "infected";
			infect(grid, r + 1, c);
			infect(grid, r - 1, c);
			infect(grid, r, c + 1);
			infect(grid, r, c - 1);
		}
	}

	public static void printStringMatrix(String[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}

	// How many subsets are there of the numbers 1...n
	// that don't contain any consecutive integers?
	// e.g. for n = 4, the subsets are {}, {1}, {2}, {3}, {4},
	// {1, 3}, {1, 4}, {2, 4}
	// The other subsets of 1,2,3,4 that DO contain consecutive integers are
	// {1,2}, {2,3}, {3,4}, {1,2,3}, {1,2,4}, {1,3,4}, {1,2,3,4}
	// Precondition: n > 0
	public static long countNonConsecutiveSubsets(int n) {
		if (n == 2) {
			return 3;
		}
		if (n == 1) {
			return 2;
		}
		return countNonConsecutiveSubsets(n - 1) + countNonConsecutiveSubsets(n - 2);
	}

	// A kid at the bottom of the stairs can jump up 1, 2, or 3 stairs at a time.
	// How many different ways can they jump up n stairs?
	// Jumping 1-1-2 is considered different than jumping 1-2-1
	// Precondition: n > 0
	public static long countWaysToJumpUpStairs(int n) {
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		if (n == 3) {
			return 4;
		}
		return countWaysToJumpUpStairs(n - 1) + countWaysToJumpUpStairs(n - 2)
				+ countWaysToJumpUpStairs(n - 3);
	}

	// Everything above this line does NOT require a recursive helper method
	// ----------------------------------
	// Everything below this line requires a recursive helper method
	// Any recursive helper method you write MUST have a comment describing:
	// 1) what the helper method does/returns
	// 2) your description must include role of each parameter in the helper method

	// Prints all the subsets of str on separate lines
	// You may assume that str has no repeated characters
	// For example, subsets("abc") would print out "", "a", "b", "c", "ab", "ac",
	// "bc", "abc"
	// Order is your choice
	public static void printSubsets(String str) {
		if (str == null) {
			System.out.println("null");
		}
		ArrayList<String> allSubsets = printSubsetsHelper(str, 0, "");
		for (String subset : allSubsets) {
			System.out.println(subset);
		}
	}

	/**
	 * Returns an ArrayList of the possible subsets by recursively adding two versions of a string:
	 * one that contains charAt(index) and one that doesn't
	 * 
	 * @param str Original String
	 * @param index Int for character in str to be added
	 * @param soFar Current built subset
	 * 
	 * @return ArrayList<String> of subsets
	 */
	private static ArrayList<String> printSubsetsHelper(String str, int index, String soFar) {
		ArrayList<String> result = new ArrayList<String>();
		if (index == str.length()) {
			result.add(soFar);
			return result;
		}

		result.addAll(printSubsetsHelper(str, index + 1, soFar));
		result.addAll(printSubsetsHelper(str, index + 1, soFar + str.charAt(index)));

		return result;
	}

	// List contains a single String to start.
	// Prints all the permutations of str on separate lines
	// You may assume that str has no repeated characters
	// For example, permute("abc") could print out "abc", "acb", "bac", "bca",
	// "cab", "cba"
	// Order is your choice
	public static void printPermutations(String str) {
		if (str == null) {
			System.out.println("null");
		}
		ArrayList<String> allPermutations = printPermutationsHelper(str, -1);
		for (String permutation : allPermutations) {
			System.out.println(permutation);
		}
	}

	/**
	 * Returns an ArrayList of all permutations of a given string by, starting from the last
	 * character in the String, adding the next letter in every possible index.
	 * 
	 * @param str Original String
	 * @param addPos Character from str to be added
	 * 
	 * @return ArrayList<String> of permutations
	 */
	private static ArrayList<String> printPermutationsHelper(String str, int addPos) {
		ArrayList<String> result = new ArrayList<String>();
		if (addPos == str.length() - 1) {
			result.add("" + str.charAt(str.length() - 1));
			return result;
		}
		result = printPermutationsHelper(str, addPos + 1);
		if (addPos < 0) {
			return result;
		}

		ArrayList<String> expanded = new ArrayList<String>(result.size() * (str.length() - addPos));
		for (int i = 0; i < result.size(); i++) {
			for (int k = 0; k < str.length() - addPos; k++) {
				expanded.add(result.get(i));
			}
		}
		result = expanded;
		for (int i = 0; i < result.size(); i++) {
			int pos = i % (str.length() - addPos);
			result.set(i, result.get(i).substring(0, pos) + str.charAt(addPos)
					+ result.get(i).substring(pos));
		}
		return result;
	}

	// Performs a mergeSort on the given array of ints
	// Precondition: you may assume there are NO duplicates!!!
	public static void mergeSort(int[] ints) {
		if (ints == null) {
			throw new IllegalArgumentException("Ints is null");
		}
		int[] sorted = mergeSortHelper(ints);
		for (int i = 0; i < sorted.length; i++) {
			ints[i] = sorted[i];
		}
	}

	/**
	 * Returns an Array of sorted integers by dividing the array into smaller iterations, comparing
	 * them individually once they reach a length of 1
	 * 
	 * @param ints int[] to be sorted
	 * 
	 * @return Sorted int[]
	 */
	private static int[] mergeSortHelper(int[] ints) {
		if (ints.length <= 1) {
			return ints;
		}
		int[] firstHalf = new int[ints.length / 2];
		for (int i = 0; i < firstHalf.length; i++) {
			firstHalf[i] = ints[i];
		}
		int[] secondHalf = new int[ints.length - firstHalf.length];
		for (int i = 0; i < secondHalf.length; i++) {
			secondHalf[i] = ints[i + firstHalf.length];
		}
		firstHalf = mergeSortHelper(firstHalf);
		secondHalf = mergeSortHelper(secondHalf);

		int[] result = new int[firstHalf.length + secondHalf.length];
		int i = 0, j = 0, addPos = 0;
		while (i < firstHalf.length && j < secondHalf.length) {
			if (firstHalf[i] <= secondHalf[j]) {
				result[addPos] = firstHalf[i];
				i++;
			} else {
				result[addPos] = secondHalf[j];
				j++;
			}
			addPos++;
		}
		while (i < firstHalf.length) {
			result[addPos] = firstHalf[i];
			addPos++;
			i++;
		}
		while (j < secondHalf.length) {
			result[addPos] = secondHalf[j];
			addPos++;
			j++;
		}
		return result;
	}

	// Performs a quickSort on the given array of ints
	// Use the middle element (index n/2) as the pivot
	// Precondition: you may assume there are NO duplicates!!!
	public static void quickSort(int[] ints) {
		if (ints == null) {
			throw new IllegalArgumentException("Ints is null");
		}
		int[] sorted = quickSortHelper(ints);
		for (int i = 0; i < sorted.length; i++) {
			ints[i] = sorted[i];
		}
	}

	/**
	 * Returns an Array of sorted integers by dividing the array in half recursively by using the
	 * middle term as a pivot, creating seperate lists that are "higher" and "lower" that are then
	 * spliced together
	 * 
	 * @param ints int[] to be sorted
	 * 
	 * @return Sorted int[]
	 */
	private static int[] quickSortHelper(int[] ints) {
		if (ints.length <= 1) {
			return ints;
		}
		int pivot = ints[ints.length / 2];
		ArrayList<Integer> firstHalfTemp = new ArrayList<Integer>(),
				secondHalfTemp = new ArrayList<Integer>();
		for (int i = 0; i < ints.length; i++) {
			if (i == ints.length / 2) {
			} else if (ints[i] < pivot) {
				firstHalfTemp.add(ints[i]);
			} else {
				secondHalfTemp.add(ints[i]);
			}
		}
		int[] firstHalf = new int[firstHalfTemp.size()],
				secondHalf = new int[secondHalfTemp.size()],
				result = new int[firstHalf.length + 1 + secondHalf.length];
		for (int i = 0; i < firstHalfTemp.size(); i++) {
			firstHalf[i] = firstHalfTemp.get(i);
		}
		for (int i = 0; i < secondHalfTemp.size(); i++) {
			secondHalf[i] = secondHalfTemp.get(i);
		}
		firstHalf = quickSortHelper(firstHalf);
		secondHalf = quickSortHelper(secondHalf);
		for (int i = 0; i < firstHalf.length; i++) {
			result[i] = firstHalf[i];
		}
		result[firstHalf.length] = pivot;
		for (int i = 0; i < secondHalf.length; i++) {
			result[i + firstHalf.length + 1] = secondHalf[i];
		}
		return result;
	}

	// Prints a sequence of moves (one on each line)
	// to complete a Towers of Hanoi problem:
	// disks start on tower 0 and must end on tower 2.
	// The towers are number 0, 1, 2, and each move should be of
	// the form "1 2", meaning "take the top disk of tower 1 and
	// put it on tower 2" etc.
	public static void solveHanoi(int startingDisks) {
		if (startingDisks < 0) {
			throw new IllegalArgumentException("startingDisks cannnot be negative");
		}
		if (startingDisks == 0) {
			System.out.println("bruh");
		}
		solveHanoiHelper(startingDisks, 0, 2, 1);
	}

	/**
	 * Solves a Tower of Hanoi problem for a given number of disks by recursively handling every set
	 * of decisions. An example of a decision set is: If the total number of disks is odd, move the
	 * smallest disk to the end tower and the one below it to the middle tower.
	 * 
	 * @param numDisks number of disks being currently considered
	 * @param currTower tower of the disk to be moved
	 * @param targetTower tower of the disk's target
	 * @param otherTower tower of the disk's indirect target (what happens after the first is moved)
	 * 
	 */
	private static void solveHanoiHelper(int numDisks, int currTower, int targetTower,
			int otherTower) {
		if (numDisks == 1) {
			System.out.println(+currTower + " -> " + targetTower);
			return;
		}
		solveHanoiHelper(numDisks - 1, currTower, otherTower, targetTower);
		System.out.println(currTower + " -> " + targetTower);
		solveHanoiHelper(numDisks - 1, otherTower, targetTower, currTower);
	}

	// // You are partaking in a scavenger hunt!
	// // You've gotten a secret map to find many of the more difficult
	// // items, but they are only available at VERY specific times at
	// // specific places. You have an array, times[], that lists at which
	// // MINUTE an item is available. Times is sorted in ascending order.
	// // Items in the ScavHunt are worth varying numbers of points.
	// // You also have an array, points[], same length as times[],
	// // that lists how many points each of the corresponding items is worth.
	// // Problem is: to get from one location to the other takes 5 minutes,
	// // so if there is an item, for example, available at time 23 and another
	// // at time 27, it's just not possible for you to make it to both: you'll
	// // have to choose!
	// // (but you COULD make it from a place at time 23 to another at time 28)
	// // Write a method that returns the maximum POINTS you can get.
	// // For example, if times = [3, 7, 9]
	// // and points = [10, 15, 10]
	// // Then the best possible result is getting the item at time 3 and the one at
	// // time 9
	// // for a total of 20 points, so it would return 20.
	public static int scavHunt(int[] times, int[] points) {
		if (times == null || points == null) {
			throw new IllegalArgumentException("Parameters cannot be null");
		}
		if (times.length != points.length) {
			throw new IllegalArgumentException(
					"Each value of points must have a corresponding time");
		}
		return scavHuntHelper(times, points, 0);
	}

	/**
	 * Finds the greatest possible sum of points for scavHunt by comparing two situations. If (1)
	 * you claim the points at the current time, time iterates by 5. If (2) you skip the current
	 * time, time iterates by 1.
	 * 
	 * @param times array of times corresponding to each points vlue
	 * @param points array of points corresponding to each times value
	 * @param time current time being considered
	 * 
	 * @return the higher number of points between the two situations
	 */
	private static int scavHuntHelper(int[] times, int[] points, int time) {
		if (time > times[times.length - 1]) {
			return 0;
		}
		if (time == times[times.length - 1]) {
			return points[points.length - 1];
		}
		int index = 0;
		for (int i = 0; i < times.length; i++) {
			if (times[i] == time) {
				index = i;
				break;
			}
			if (times[i] > time) {
				index = i;
				break;
			}
		}
		int take = points[index] + scavHuntHelper(times, points, times[index] + 5);
		int takent = scavHuntHelper(times, points, times[index] + 1);
		return take > takent ? take : takent;
	}
}
