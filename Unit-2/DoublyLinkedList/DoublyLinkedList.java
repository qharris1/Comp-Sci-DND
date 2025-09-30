
public class DoublyLinkedList {
	// Implements a circular doubly-linked list.

	private final ListNode2<Nucleotide> SENTINEL = new ListNode2<Nucleotide>(null);
	private int nodeCount;

	// Constructor: creates an empty list
	public DoublyLinkedList() {
		nodeCount = 0;
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public DoublyLinkedList(Nucleotide[] values) {
		for (int i = 0; i < values.length; i++) {
			add(values[i]);
		}
	}

	public ListNode2<Nucleotide> getSentinel() {
		return SENTINEL;
	}

	public ListNode2<Nucleotide> getHead() {
		return SENTINEL.getNext();
	}

	public ListNode2<Nucleotide> getTail() {
		return SENTINEL.getPrevious();
	}


	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
		return nodeCount == 0;
	}

	// Returns the number of elements in this list.
	public int size() {
		return nodeCount;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(Nucleotide obj) {
		return indexOf(obj) != -1;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(Nucleotide obj) {
		ListNode2<Nucleotide> currObject = SENTINEL.getNext();
		for (int i = 0; i < nodeCount; i++) {
			if (currObject.getValue().equals(obj)) {
				return i;
			}
			currObject = currObject.getNext();
		}
		return -1;
	}

	// Adds obj to this collection. Returns true if successful;
	// otherwise returns false.
	public boolean add(Nucleotide obj) {
		if (nodeCount == 0) {
			ListNode2<Nucleotide> add =
					new ListNode2<Nucleotide>(obj, getSentinel(), getSentinel());
			SENTINEL.setNext(add);
			SENTINEL.setPrevious(add);
		} else {
			ListNode2<Nucleotide> tail = new ListNode2<Nucleotide>(obj, getTail(), null);
			getTail().setNext(tail);
			SENTINEL.setPrevious(tail);
		}
		nodeCount++;
		return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(Nucleotide obj) {
		int i = indexOf(obj);
		if (i != -1) {
			remove(i);
			return true;
		}
		return false;
	}

	// Returns the i-th element.
	public Nucleotide get(int i) {
		ListNode2<Nucleotide> currObject = SENTINEL.getNext();
		for (int index = 0; index < i; index++) {
			currObject = currObject.getNext();
		}
		return currObject.getValue();
	}

	// Replaces the i-th element with obj and returns the old value.
	public Nucleotide set(int i, Nucleotide obj) {
		add(i, obj);
		return remove(i + 1);
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Nucleotide obj) {
		if (i == 0) {
			getSentinel().setNext(
					new ListNode2<Nucleotide>(obj, getSentinel(), getSentinel().getNext()));
		} else {
			ListNode2<Nucleotide> currObject = SENTINEL.getNext();
			for (int index = 0; index < i - 1; index++) {
				currObject = currObject.getNext();
			}
			currObject.setNext(new ListNode2<Nucleotide>(obj, currObject, currObject.getNext()));
		}
		nodeCount++;
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public Nucleotide remove(int i) {
		Nucleotide value;
		if (i == nodeCount) {
			value = getTail().getValue();
			getSentinel().setPrevious(getTail().getPrevious());
		} else {
			ListNode2<Nucleotide> currObject = SENTINEL.getNext();
			for (int index = 0; index < i; index++) {
				currObject = currObject.getNext();
			}
			value = currObject.getValue();
			currObject.setValue(currObject.getNext().getValue());
			currObject.setNext(currObject.getNext().getNext());
		}
		nodeCount--;
		return value;
	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		ListNode2<Nucleotide> currObject = SENTINEL.getNext();
		StringBuilder returned = new StringBuilder("[");
		for (int i = 0; i < nodeCount - 1; i++) {
			returned.append(currObject.getValue() + ", ");
			currObject = currObject.getNext();
		}
		returned.append(getTail().getValue() + "]");
		return returned.toString();
	}

	// Like question 7 on the SinglyLinkedList test:
	// Add a "segment" (another list) onto the end of this list
	public void addSegmentToEnd(DoublyLinkedList seg) {

	}

	// Like question 8 on the SinglyLinkedList test:
	// You are to remove the next 16 nodes from the list, after the node nodeBefore.
	// (on the test these nodes were assumed to contain CCCCCCCCGGGGGGGG, but here
	// you do not need to assume or check for that)
	public void removeCCCCCCCCGGGGGGGG(ListNode2<Nucleotide> nodeBefore) {

	}

	// Like question 9 on the SinglyLinkedList test:
	// You are to find and delete the first instance of seg in the list.
	// If seg is not in the list, return false, otherwise return true.
	public boolean deleteSegment(DoublyLinkedList seg) {
		return false;
	}

	// Like question 10 on the SinglyLinkedList test:
	// Delete the last three nodes in the list
	// If there are not enough nodes, return false
	public boolean deleteLastThree() {
		return false;
	}

	// Like question 11 on the SinglyLinkedList test:
	// Replaces every node containing "A" with three nodes containing "T" "A" "C"
	public void replaceEveryAWithTAC() {

	}

}
