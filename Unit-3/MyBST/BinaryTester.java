public class BinaryTester {
    public static void main(String[] args) {
        MyBST<Integer> bst = new MyBST<Integer>();
        bst.add(30);
        bst.add(25);
        bst.add(15);
        bst.add(5);
        bst.add(45);
        bst.add(22);

        // System.out.println(bst.toString());
        // bst.remove(5);
        // System.out.println(bst.toString());
        // System.out.println(bst.getRoot());

        // System.out.println(bst.contains(5));

        System.out.println(bst.getHeight());
        System.out.println(bst.getRoot().getLeft().getLeft().getLeft().getHeight());
    }
}
