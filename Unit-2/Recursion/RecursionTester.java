public class RecursionTester {
    public static void main(String[] args) {
        ListNode linkedList = new ListNode("First", new ListNode("Second", new ListNode("Third")));
        Recursion.printListInReverse(linkedList);

        String[] grod = new String[] {"healthy", "healthy", "vaccinated"};

        String[] grad = new String[] {"vaccinated", "healthy", "healthy"};

        String[][] griddy = new String[][] {grad, grod, grod};

        Recursion.printStringMatrix(griddy);

        Recursion.infect(griddy, 1, 1);

        Recursion.printStringMatrix(griddy);

        System.out.println(Recursion.countNonConsecutiveSubsets(6));

        System.out.println(Recursion.countWaysToJumpUpStairs(5));

        Recursion.printSubsets("ab");

        Recursion.printPermutations("abcd");

        Recursion.mergeSort(new int[] {38, 27, 43, 10});

        Recursion.quickSort(new int[] {4, 3, 1, 2, 5, 9, 7, 10, 6});

        Recursion.solveHanoi(3);

        int[] times = {2, 6, 7, 20, 21, 40, 41, 43, 45, 47, 51, 53, 62, 63, 64};
        int[] points = {1000000, 4, 7, 300, 8, 20, 251, 23, 21, 1220, 8, 9, 14, 81, 82};
        System.out.println(Recursion.scavHunt(times, points));
    }
}
