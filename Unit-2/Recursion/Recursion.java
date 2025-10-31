import java.util.ArrayList;

public class Recursion {

	// Prints the value of every node in the singly linked list with the given head,
	// but in reverse
	public static void printListInReverse(ListNode head) {
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
			return n;
		}
		if (n == 1) {
			return 1;
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
		ArrayList<String> allSubsets = printSubsetsHelper(str, 0, "");
		for (String subset : allSubsets) {
			System.out.println(subset);
		}
	}

	/**
	 * Returns an ArrayList of the possible subsets by recursively adding two versions of a string:
	 * one that contains charAt(index) and one that doesn't
	 * 
	 * @param String str -> Original String
	 * @param Integer index -> Character to be added
	 * @param String soFar -> Current built subset
	 * 
	 * @return ArrayList<String>
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


	private static ArrayList<String> getPermutations(String str, int length) {
		ArrayList<String> result = new ArrayList<String>();
		if (length == 1) {
			result.add("" + str.charAt(str.length() - 1));
			return result;
		}

		result = getPermutations(str, length - 1);
		for (int i = 0; i < length; i++) {
			result.add(i + 1, result.get(i));
			i++;
		}
		for (int i = 0; i < result.size(); i++) {
			String temp = result.get(i).substring(0, i);
			if (i == 0) {
				result.set(i, str.charAt(str.length() - length) + temp);
			} else {
				result.set(i, temp + str.charAt(str.length() - length));
			}
		}

		return result;
	}

	// List contains a single String to start.
	// Prints all the permutations of str on separate lines
	// You may assume that str has no repeated characters
	// For example, permute("abc") could print out "abc", "acb", "bac", "bca",
	// "cab", "cba"
	// Order is your choice
	public static void printPermutations(String str) {
		for (int i = 0; i < str.length(); i++) {
			System.out.println(getPermutations(str, str.length()));
		}
	}

	// Performs a mergeSort on the given array of ints
	// Precondition: you may assume there are NO duplicates!!!
	public static void mergeSort(int[] ints) {

	}

	// Performs a quickSort on the given array of ints
	// Use the middle element (index n/2) as the pivot
	// Precondition: you may assume there are NO duplicates!!!
	public static void quickSort(int[] ints) {

	}

	// Prints a sequence of moves (one on each line)
	// to complete a Towers of Hanoi problem:
	// disks start on tower 0 and must end on tower 2.
	// The towers are number 0, 1, 2, and each move should be of
	// the form "1 -> 2", meaning "take the top disk of tower 1 and
	// put it on tower 2" etc.
	public static void solveHanoi(int startingDisks) {

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
	// public static int scavHunt(int[] times, int[] points) {

	// }

}
