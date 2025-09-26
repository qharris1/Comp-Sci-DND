// Implements a singly-linked list.


public class SinglyLinkedList<E> {
	private ListNode<E> head;
	private ListNode<E> tail;
	private int nodeCount;

	// Constructor: creates an empty list
	public SinglyLinkedList() {
		this.head = null;
		this.tail = null;
		this.nodeCount = 0;
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	@SuppressWarnings("unchecked")
	public SinglyLinkedList(Object[] values) {
		for (int i = 0; i < values.length; i++) {
			add((E) values[i]);
		}
	}

	public ListNode<E> getHead() {
		return head;
	}

	public ListNode<E> getTail() {
		return tail;
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
	public boolean contains(E obj) {
		ListNode<E> currObject = head;
		while (currObject != null) {
			if (currObject.getValue().equals(obj)) {
				return true;
			}
			currObject = currObject.getNext();
		}
		return false;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(E obj) {
		ListNode<E> currObject = head;
		int i = 0;
		while (currObject != null) {
			if (currObject.getValue().equals(obj)) {
				return i;
			}
			currObject = currObject.getNext();
			i++;
		}
		return -1;
	}

	// Adds obj to this collection. Returns true if successful;
	// otherwise returns false.
	public boolean add(E obj) {
		add(nodeCount, obj);
		return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(E obj) {
		int index = indexOf(obj);
		if (index != -1) {
			remove(index);
			return true;
		}
		nodeCount--;
		return false;
	}

	// Returns the i-th element.
	public E get(int i) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException("Index: " + i + " out of bounds for list");
		}
		ListNode<E> currObject = head;
		int currPos = 0;
		while (currObject != null) {
			if (currPos == i) {
				return currObject.getValue();
			}
			currPos++;
			currObject = currObject.getNext();
		}
		return null;
	}

	// Replaces the i-th element with obj and returns the old value.
	public E set(int i, Object obj) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException("Index: " + i + " out of bounds for list");
		}
		ListNode<E> currObject = head;
		for (int index = 0; index < i; index++) {
			currObject = currObject.getNext();
		}
		E oldValue = currObject.getValue();
		currObject.setValue((E) obj);
		return oldValue;
	}

	// Inserts obj at the i-th position. Increments the size by one.
	public void add(int i, Object obj) {
		if (i < 0 || i > nodeCount)
			throw new IndexOutOfBoundsException("Index: " + i + " out of bounds for list");
		ListNode<E> newNode = new ListNode<E>((E) obj, null);
		if (i == 0) {
			newNode.setNext(head);
			head = newNode;
			if (nodeCount == 0) {
				tail = newNode;
			}
		} else if (i == nodeCount) {
			if (tail != null) {
				tail.setNext(newNode);
			}
			tail = newNode;
			if (nodeCount == 0) {
				head = newNode;
			}
		} else {
			ListNode<E> currObject = head;
			for (int index = 0; index < i - 1; index++) {
				currObject = currObject.getNext();
			}
			newNode.setNext(currObject.getNext());
			currObject.setNext(newNode);
		}
		nodeCount++;
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public E remove(int i) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException("Index: " + i + " out of bounds for list");
		}
		ListNode<E> removed;
		if (i == 0) {
			removed = head;
			head = head.getNext();
		} else {
			ListNode<E> currObject = head;
			for (int index = 0; index < i - 1; index++) {
				currObject = currObject.getNext();
			}
			removed = currObject.getNext();
			currObject.setNext(removed.getNext());
			if (removed == tail) {
				tail = currObject;
			}
		}
		nodeCount--;
		return removed.getValue();
	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		ListNode<E> currObject = head;
		while (currObject != tail) {
			str.append(currObject.getValue() + ", ");
			currObject = currObject.getNext();
		}
		str.append(tail.getValue());
		return str.toString() + ']';
	}
}
