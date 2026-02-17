import java.util.ArrayList;

public class PQTester {
    public static void main(String[] args) {
        HeapPQ<Integer> pq = new HeapPQ<Integer>();
        ArrayList<Integer> sortedList = new ArrayList<Integer>();

        pq.add(10);
        pq.add(6);
        pq.add(11);
        pq.add(8);
        pq.add(9);
        pq.add(5);
        pq.add(7);
        sortedList.add(5);
        sortedList.add(6);
        sortedList.add(7);
        sortedList.add(8);
        sortedList.add(9);
        sortedList.add(10);
        sortedList.add(11);

        System.out.println(pq);

        for (int i = 0; i < sortedList.size(); i++) {
            System.out.println(pq.peek());
            System.out.println(pq.removeMin());
            System.out.println();
        }
    }
}
