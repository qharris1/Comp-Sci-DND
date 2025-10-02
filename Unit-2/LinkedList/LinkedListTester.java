public class LinkedListTester {
    public static void main(String[] args) {
        String a = "A";
        String[] strs = new String[] {"A"};
        SinglyLinkedList<String> list = new SinglyLinkedList<>(strs);
        System.out.println(list.toString());
        // System.out.println(list.contains(a));
        // System.out.println(list.indexOf(a));
        // System.out.println(list.get(2));

        list.set(0, null);

        list.add("A");

        System.out.println(list.toString());

        list.set(1, "null");

        System.out.println(list.indexOf(null));

        System.out.println(list.contains("null"));

        // list.set(0, "Y");
        // System.out.println(list.toString());
    }
}
