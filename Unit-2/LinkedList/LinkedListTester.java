public class LinkedListTester {
    public static void main(String[] args) {
        String a = "A";
        String[] strs = new String[] {"Y", a, "R", "I"};
        SinglyLinkedList<String> list = new SinglyLinkedList<>(strs);
        System.out.println(list.toString());
        // System.out.println(list.contains(a));
        // System.out.println(list.indexOf(a));
        // System.out.println(list.get(2));

        list.remove(3);
        System.out.println(list.toString());

		list.add(0, "I");

		System.out.println(list.toString());

        // list.set(0, "Y");
        // System.out.println(list.toString());
    }
}
