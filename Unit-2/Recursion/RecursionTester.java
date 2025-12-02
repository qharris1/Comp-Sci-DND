public class RecursionTester {
    public static void main(String[] args) {
        // ListNode linkedList = new ListNode("First", new ListNode("Second", new
        // ListNode("Third")));
        // Recursion.printListInReverse(linkedList);

        // String[] grod = new String[] {"healthy", "healthy", "vaccinated"};

        // String[] grad = new String[] {"vaccinated", "healthy", "healthy"};

        // String[][] griddy = new String[][] {grad, grod, grod};

        // Recursion.printStringMatrix(griddy);

        // Recursion.infect(griddy, 1, 1);

        // Recursion.printStringMatrix(griddy);

        // System.out.println(Recursion.countNonConsecutiveSubsets(5));

        // System.out.println(Recursion.countWaysToJumpUpStairs(5));

        // Recursion.printSubsets(null);

        // Recursion.printPermutations("abcd");
        int[] arr = new int[1000000];

        for (int i = 0; i < 1000000; i++) {
            arr[i] = 1000000 - i;
        }
        System.out.println("Done Adding");
        // Recursion.mergeSort(arr);

        // Recursion.quickSort(arr);

        // Recursion.solveHanoi(0);

        // int[] times = {0};
        // int[] points = {0};
        System.out.println(Recursion.scavHunt(arr, arr));
    }
}
