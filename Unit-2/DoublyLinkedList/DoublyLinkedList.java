
public class DoublyLinkedList {
	// Implements a circular doubly-linked list.

	private final ListNode2<Nucleotide> SENTINEL = new ListNode2<Nucleotide>(null);
	private int nodeCount;

	// Constructor: creates an empty list
	// O(1)
	public DoublyLinkedList() {
		nodeCount = 0;
		SENTINEL.setNext(SENTINEL);
		SENTINEL.setPrevious(SENTINEL);
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	// O(n)
	public DoublyLinkedList(Nucleotide[] values) {
		if (values == null) {
			throw new IllegalArgumentException("Constructor values cannot be null");
		}
		for (int i = 0; i < values.length; i++) {
			add(values[i]);
		}
	}

	// O(1)
	public ListNode2<Nucleotide> getSentinel() {
		return SENTINEL;
	}

	// O(1)
	public ListNode2<Nucleotide> getHead() {
		return SENTINEL.getNext();
	}

	// O(1)
	public ListNode2<Nucleotide> getTail() {
		return SENTINEL.getPrevious();
	}

	// Returns true if this list is empty; otherwise returns false.
	// O(1)
	public boolean isEmpty() {
		return nodeCount == 0;
	}

	// Returns the number of elements in this list.
	// O(1)
	public int size() {
		return nodeCount;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	// Best: O(1) Worst: O(n)
	public boolean contains(Nucleotide obj) {
		return indexOf(obj) != -1;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	// Best: O(1) Worst: O(n)
	public int indexOf(Nucleotide obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Obj cannot be null");
		}
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
	// O(1)
	public boolean add(Nucleotide obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Obj cannot be null");
		}
		if (nodeCount == 0) {
			ListNode2<Nucleotide> add =
					new ListNode2<Nucleotide>(obj, getSentinel(), getSentinel());
			SENTINEL.setNext(add);
			SENTINEL.setPrevious(add);
		} else {
			ListNode2<Nucleotide> tail = new ListNode2<Nucleotide>(obj, getTail(), getSentinel());
			getTail().setNext(tail);
			SENTINEL.setPrevious(tail);
			tail.getPrevious().setNext(tail);
		}
		nodeCount++;
		return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	// Best: O(1) Worst: O(n)
	public boolean remove(Nucleotide obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Obj cannot be null");
		}
		int i = indexOf(obj);
		if (i != -1) {
			remove(i);
			return true;
		}
		return false;
	}

	// Returns the i-th element.
	// O(1)
	public Nucleotide get(int i) {
		if (i < 0 || i > nodeCount - 1) {
			throw new IndexOutOfBoundsException("Get index out of bounds");
		}
		ListNode2<Nucleotide> currObject = SENTINEL.getNext();
		for (int index = 0; index < i; index++) {
			currObject = currObject.getNext();
		}
		return currObject.getValue();
	}

	// Replaces the i-th element with obj and returns the old value.
	// Best: O(1) Worst: O(n)
	public Nucleotide set(int i, Nucleotide obj) {
		if (i < 0 || i > nodeCount - 1) {
			throw new IndexOutOfBoundsException("Set index out of bounds");
		}
		if (obj == null) {
			throw new IllegalArgumentException("Set obj cannot be null");
		}
		add(i, obj);
		return remove(i + 1);
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	// Best: O(1) Worst: O(n)
	public void add(int i, Nucleotide obj) {
		if (i < 0 || i > nodeCount) {
			throw new IndexOutOfBoundsException("Add index out of bounds");
		}
		if (obj == null) {
			throw new IllegalArgumentException("Add obj cannot be null");
		}
		if (i == 0) {
			ListNode2<Nucleotide> newNode =
					new ListNode2<Nucleotide>(obj, getSentinel(), getSentinel().getNext());
			getSentinel().setNext(newNode);
			newNode.getNext().setPrevious(newNode);
		} else if (i == nodeCount) {
			getSentinel().setPrevious(
					new ListNode2<Nucleotide>(obj, getSentinel().getPrevious(), getSentinel()));
		} else {
			ListNode2<Nucleotide> currObject = SENTINEL.getNext();
			for (int index = 0; index < i - 1; index++) {
				currObject = currObject.getNext();
			}
			ListNode2<Nucleotide> addition =
					new ListNode2<Nucleotide>(obj, currObject, currObject.getNext());
			addition.getNext().setPrevious(addition);
			currObject.setNext(addition);
		}
		nodeCount++;
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	// Best: O(1) Worst: O(n)
	public Nucleotide remove(int i) {
		if (i < 0 || i > nodeCount - 1) {
			throw new IndexOutOfBoundsException("Remove index out of bounds");
		}
		Nucleotide value;
		if (i == nodeCount - 1) {
			value = getTail().getValue();
			getSentinel().setPrevious(getTail().getPrevious());
			getTail().setNext(getSentinel());
		} else {
			ListNode2<Nucleotide> currObject = SENTINEL.getNext();
			for (int index = 0; index < i; index++) {
				currObject = currObject.getNext();
			}
			value = currObject.getValue();
			currObject.getNext().setPrevious(currObject.getPrevious());
			currObject.getPrevious().setNext(currObject.getNext());
		}
		nodeCount--;
		return value;
	}

	// Returns a string representation of this list exactly like that for
	// MyArrayList.
	// O(n)
	public String toString() {
		ListNode2<Nucleotide> currObject = getSentinel().getNext();
		StringBuilder returned = new StringBuilder("[");
		if (currObject != getSentinel()) {
			for (int i = 0; i < nodeCount - 1; i++) {
				returned.append(currObject.getValue());
				returned.append(", ");
				currObject = currObject.getNext();
			}
			returned.append(getTail().getValue());
		}
		return returned.toString() + "]";
	}

	// Returns a string representation of this list exactly like that for
	// MyArrayList.
	// O(n)
	public String toStringReverse() {
		ListNode2<Nucleotide> currObject = SENTINEL.getPrevious();
		StringBuilder returned = new StringBuilder("[");
		for (int i = 0; i < nodeCount - 1; i++) {
			returned.append(currObject.getValue());
			returned.append(", ");
			currObject = currObject.getPrevious();
		}
		returned.append(getHead().getValue());
		return returned.toString() + "]";
	}

	// Like question 7 on the SinglyLinkedList test:
	// Add a "segment" (another list) onto the end of this list
	// O(1)
	public void addSegmentToEnd(DoublyLinkedList seg) {
		if (seg == null) {
			throw new IllegalArgumentException("Additional segment cannot be null");
		}
		if (seg.nodeCount != 0) {
			getTail().setNext(seg.getHead());
			getTail().getNext().setPrevious(getTail());
			SENTINEL.setPrevious(seg.getTail());
			nodeCount += seg.nodeCount;
		}
	}

	// Like question 8 on the SinglyLinkedList test:
	// You are to remove the next 16 nodes from the list, after the node nodeBefore.
	// (on the test these nodes were assumed to contain CCCCCCCCGGGGGGGG, but here
	// you do not need to assume or check for that)
	// O(1)
	public void removeCCCCCCCCGGGGGGGG(ListNode2<Nucleotide> nodeBefore) {
		ListNode2<Nucleotide> node = nodeBefore;
		for (int i = 0; i <= 16; i++) {
			if (node == SENTINEL) {
				throw new IllegalArgumentException("Not enough nodes to remove 16");
			}
			node = node.getNext();
		}
		nodeBefore.setNext(node);
		nodeCount -= 16;
	}

	// Best: O(n) Worst: O(n^2)
	private ListNode2<Nucleotide> findSegment(DoublyLinkedList seg) {
		ListNode2<Nucleotide> currNode = getHead();
		boolean found = false;
		for (int i = 0; i < nodeCount; i++) {
			if (currNode.getValue().equals(seg.getHead().getValue())) {
				ListNode2<Nucleotide> tempNode = currNode.getNext();
				ListNode2<Nucleotide> segNode = seg.getHead().getNext();
				for (int j = 1; j < seg.nodeCount; j++) {
					if (!tempNode.getValue().equals(segNode.getValue())) {
						found = false;
						break;
					} else {
						found = true;
					}
					tempNode = tempNode.getNext();
					segNode = segNode.getNext();
				}
			}
			if (found) {
				return currNode.getPrevious();
			}
		}
		return null;
	}

	// Like question 9 on the SinglyLinkedList test:
	// You are to find and delete the first instance of seg in the list.
	// If seg is not in the list, return false, otherwise return true.
	// Best: O(n) Worst: O(n^2)
	public boolean deleteSegment(DoublyLinkedList seg) {
		if (seg == null) {
			throw new IllegalArgumentException("Removed segment cannot be null");
		}
		ListNode2<Nucleotide> start = findSegment(seg);
		ListNode2<Nucleotide> currNode = start;
		if (start != null) {
			for (int i = 0; i < seg.nodeCount; i++) {
				currNode = currNode.getNext();
			}
			if (currNode == getTail()) {
				getSentinel().setNext(getSentinel());
				getSentinel().setPrevious(getSentinel());
				nodeCount = 0;
			} else {
				start.setNext(currNode.getNext());
				nodeCount -= seg.nodeCount;
			}
			return true;
		}
		return false;
	}

	// Like question 10 on the SinglyLinkedList test:
	// Delete the last three nodes in the list
	// If there are not enough nodes, return false
	// Best: O(1) Worst: O(n)
	public boolean deleteLastThree() {
		if (nodeCount < 3) {
			return false;
		}
		for (int i = 0; i < 3; i++) {
			remove(nodeCount - 1);
		}
		return true;
	}

	// Like question 11 on the SinglyLinkedList test:
	// Replaces every node containing "A" with three nodes containing "T" "A" "C"
	// O(n)
	public void replaceEveryAWithTAC() {
		ListNode2<Nucleotide> currNode = getHead();
		if (indexOf(Nucleotide.A) == -1) {
			return;
		}
		for (int i = 0; i < nodeCount; i++) {
			if (currNode.getValue().equals(Nucleotide.A)) {
				set(i, Nucleotide.T);
				if (i + 1 < nodeCount) {
					add(i + 1, Nucleotide.A);
				} else {
					add(Nucleotide.A);
				}
				i++;
				if (i + 1 < nodeCount) {
					add(i + 1, Nucleotide.C);
				} else {
					add(Nucleotide.C);
				}
				i++;
			}
			currNode = currNode.getNext();
		}
	}

}
