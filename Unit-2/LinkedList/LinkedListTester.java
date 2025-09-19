public class LinkedListTester {
    public static void main(String[] args) {
        String[] strs = new String[] {"Y", "A", "R", "I"};
        SinglyLinkedList<String> list = new SinglyLinkedList<>(strs);
        System.out.println(list.toString());
    }
}
