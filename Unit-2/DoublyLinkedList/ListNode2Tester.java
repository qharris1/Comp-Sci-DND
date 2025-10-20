public class ListNode2Tester {
    public static void main(String[] args) {
        Nucleotide[] nucleotides = new Nucleotide[] {Nucleotide.A};
        DoublyLinkedList mylist = new DoublyLinkedList(nucleotides);

        System.out.println(mylist);

        for (int i = 0; i < 18; i++) {
            if (i % 2 == 0) {
                mylist.addSegmentToEnd(new DoublyLinkedList(new Nucleotide[] {Nucleotide.C}));
            } else {
                mylist.addSegmentToEnd(new DoublyLinkedList(new Nucleotide[] {Nucleotide.A}));
            }

        }

        System.out.println(mylist);

        mylist.removeCCCCCCCCGGGGGGGG(mylist.getSentinel());

        System.out.println(mylist);

        System.out.println(mylist.toStringReverse());

        System.out.println(mylist.deleteLastThree());

        System.out.println(mylist.toStringReverse());

        System.out.println(mylist);

        mylist.replaceEveryAWithTAC();

        System.out.println(mylist);

        mylist.addSegmentToEnd(new DoublyLinkedList(new Nucleotide[] {Nucleotide.C}));

        System.out.println(mylist);

        System.out.println(mylist.toStringReverse());
    }
}
