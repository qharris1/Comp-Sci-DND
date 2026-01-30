import java.io.*;
import java.util.*;

// You are allowed (and expected!) to use either Java's ArrayDeque or LinkedList class to make stacks and queues

public class CookieMonster {

	private int[][] cookieGrid;
	private int numRows;
	private int numCols;

	// Constructs a CookieMonster from a file with format:
	// numRows numCols
	// <<rest of the grid, with spaces in between the numbers>>
	public CookieMonster(String fileName) {
		int row = 0;
		int col = 0;
		try {
			Scanner input = new Scanner(new File(fileName));

			numRows = input.nextInt();
			numCols = input.nextInt();
			cookieGrid = new int[numRows][numCols];

			for (row = 0; row < numRows; row++)
				for (col = 0; col < numCols; col++)
					cookieGrid[row][col] = input.nextInt();

			input.close();
		} catch (Exception e) {
			System.out.print("Error creating maze: " + e.toString());
			System.out.println("Error occurred at row: " + row + ", col: " + col);
		}

	}

	public CookieMonster(int[][] cookieGrid) {
		this.cookieGrid = cookieGrid;
		this.numRows = cookieGrid.length;
		this.numCols = cookieGrid[0].length;
	}

	// You may find it VERY helpful to write this helper method. Or not!
	private boolean validPoint(int row, int col) {
		return !((row > cookieGrid.length - 1 || col > cookieGrid[row].length - 1) || (cookieGrid[row][col] == -1));
	}

	/*
	 * RECURSIVELY calculates the route which grants the most cookies.
	 * Returns the maximum number of cookies attainable.
	 */
	public int recursiveCookies() {
		return recursiveCookies(0, 0);
	}

	// Returns the maximum number of cookies edible starting from (and including)
	// cookieGrid[row][col]
	public int recursiveCookies(int row, int col) {
		if (!validPoint(row, col) || cookieGrid[row][col] == -1) {
			return 0;
		} else {
			int sum = cookieGrid[row][col], goRight = recursiveCookies(row, col + 1),
					goLeft = recursiveCookies(row + 1, col);
			sum += goRight > goLeft ? goRight : goLeft;
			return sum;
		}
	}

	/*
	 * Calculate which route grants the most cookies using a QUEUE.
	 * Returns the maximum number of cookies attainable.
	 */
	/*
	 * From any given position, always add the path right before adding the path
	 * down
	 */
	public int queueCookies() {
		ArrayDeque<OrphanScout> queue = new ArrayDeque<OrphanScout>();
		queue.add(new OrphanScout(0, 0, cookieGrid[0][0]));
		boolean cease = false;
		while (!cease) {
			cease = true;
			int length = queue.size();
			for (int i = 0; i < length; i++) {
				OrphanScout curr = queue.peek();
				int currentRow = curr.getEndingRow(), currentCol = curr.getEndingCol(),
						numCookies = curr.getCookiesDiscovered();
				if (validPoint(currentRow, currentCol + 1)) {
					queue.add(new OrphanScout(
							currentRow,
							currentCol + 1,
							numCookies + cookieGrid[currentRow][currentCol + 1]));
				}

				if (validPoint(currentRow + 1, currentCol)) {
					queue.add(new OrphanScout(
							currentRow + 1,
							currentCol,
							numCookies + cookieGrid[currentRow + 1][currentCol]));
				}
				queue.remove();
			}
			length = queue.size();
			for (int i = 0; i < length; i++) {
				OrphanScout curr = queue.remove();
				if (curr.getEndingRow() != numRows - 1 ||
						curr.getEndingCol() != numCols - 1) {
					cease = false;
				}
				queue.add(curr);
			}
		}
		int max = 0;
		while (!queue.isEmpty()) {
			int cookies = queue.remove().getCookiesDiscovered();
			if (cookies > max) {
				max = cookies;
			}
		}

		return max;
	}

	/*
	 * Calculate which route grants the most cookies using a stack.
	 * Returns the maximum number of cookies attainable.
	 */
	/*
	 * From any given position, always add the path right before adding the path
	 * down
	 */
	public int stackCookies() {
		Stack<OrphanScout> stack = new Stack<OrphanScout>();
		stack.add(new OrphanScout(0, 0, cookieGrid[0][0]));
		int max = 0;

		return max;
	}
}
