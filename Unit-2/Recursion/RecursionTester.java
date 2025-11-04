public class RecursionTester {
    public static void main(String[] args) {
        // ListNode linkedList = new ListNode("First", new ListNode("Second", new
        // ListNode("Third")));
        // Recursion.printListInReverse(linkedList);

        String[] grod = new String[] {"healthy", "healthy", "vaccinated"};

        String[] grad = new String[] {"vaccinated", "healthy", "healthy"};

        String[][] griddy = new String[][] {grad, grod, grod};

        Recursion.printStringMatrix(griddy);

        Recursion.infect(griddy, 1, 1);

        Recursion.printStringMatrix(griddy);

        System.out.println(Recursion.countNonConsecutiveSubsets(6));

        System.out.println(Recursion.countWaysToJumpUpStairs(5));

        // Recursion.printSubsets("ab");

        Recursion.printPermutations("abcd");
    }
}
