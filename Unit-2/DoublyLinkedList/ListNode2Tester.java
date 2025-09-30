public class ListNode2Tester {
    public static void main(String[] args) {
        Nucleotide[] nucleotides = new Nucleotide[]{Nucleotide.A, Nucleotide.G, Nucleotide.T, Nucleotide.C};
        DoublyLinkedList list = new DoublyLinkedList(nucleotides);

        System.out.println(list.toString());

        list.add(Nucleotide.T);

        System.out.println(list.toString());

        list.remove(Nucleotide.T);

        System.out.println(list.toString());

        list.add(3, Nucleotide.T);

        System.out.println(list.toString());

        list.set(4, Nucleotide.A);

        System.out.println(list.toString());
    }
}
