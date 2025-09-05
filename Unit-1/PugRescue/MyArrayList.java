/*
 * See ArrayList documentation here:
 * http://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
 */

/*
 * Your indexed functions should throw IndexOutOfBoundsException if index is invalid!
 */

public class MyArrayList<E> {

	/* Internal Object counter */
	protected int objectCount;

	/* Internal Object array */
	protected E[] internalArray;

	/* Constructor: Create it with whatever capacity you want? */
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		this.internalArray = (E[]) new Object[100];
	}

	/* Constructor with initial capacity */
	@SuppressWarnings("unchecked")
	public MyArrayList(int initialCapacity) {
		this.internalArray = (E[]) new Object[initialCapacity];
	}

	/* Return the number of active slots in the array list */
	// O(1)
	public int size() {
		return objectCount;
	}

	/* Are there zero objects in the array list? */
	// O(1)
	public boolean isEmpty() {
		return objectCount == 0;
	}

	/* Get the index-th object in the list. */
	// O(1)
	public E get(int index) {
		return internalArray[index];
	}

	/* Replace the object at index with obj. returns object that was replaced. */
	// O(1)
	public E set(int index, E obj) {
		E temp = internalArray[index];
		internalArray[index] = obj;
		return temp;
	}

	/*
	 * Returns true if this list contains an element equal to obj; otherwise returns false. Best:
	 * O(1) (when obj is early in the array); Worst: O(n)
	 */
	public boolean contains(E obj) {
		for (int i = 0; i < internalArray.length; i++) {
			if (internalArray[i] == null) {
				if (obj == internalArray[i]) {
					return true;
				}
			} else {
				if (internalArray[i].equals(obj)) {
					return true;
				}
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	/*
	 * Doubles the size of the array if there are no more available slots when trying to add an obj
	 * O(n)
	 */
	private void checkExpandArray() {
		if (objectCount >= internalArray.length) {
			E[] newArray = (E[]) new Object[internalArray.length * 2];
			for (int i = 0; i < internalArray.length; i++) {
				newArray[i] = internalArray[i];
			}
			internalArray = newArray;
		}
	}

	/* Insert an object at index */
	public void add(int index, E obj) {
		checkExpandArray();
		E temp = set(index, obj);
		for (int i = index + 1; i < internalArray.length; i++) {
			temp = set(i, temp);
		}
		objectCount++;
	}

	/* Add an object to the end of the list; returns true */
	public boolean add(E obj) {
		add(objectCount, obj);
		return true;
	}

	/* Remove the object at index and shift. Returns removed object. */
	public E remove(int index) {
		E temp = set(objectCount, null);
		for (int i = objectCount - 1; i >= index; i--) {
			temp = set(i, temp);
		}
		objectCount--;
		return temp;
	}

	/*
	 * Removes the first occurrence of the specified element from this list, if it is present. If
	 * the list does not contain the element, it is unchanged. More formally, removes the element
	 * with the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))) (if such an
	 * element exists). Returns true if this list contained the specified element (or equivalently,
	 * if this list changed as a result of the call).
	 */
	public boolean remove(E obj) {
		for (int i = 0; i < internalArray.length; i++) {
			if (internalArray[i] == obj) {
				if (obj == internalArray[i]) {
					remove(i);
					return true;
				}
			} else {
				if (internalArray[i] != null && internalArray[i].equals(obj)) {
					remove(i);
					return true;
				}
			}

		}
		return false;
	}

	/*
	 * For testing; your string should output as "[X, X, X, X, ...]" where X, X, X, X, ... are the
	 * elements in the ArrayList. If the array is empty, it should return "[]". If there is one
	 * element, "[X]", etc. Elements are separated by a comma and a space.
	 */
	public String toString() {
		String print = "[";
		for (int i = 0; i < internalArray.length; i++) {
			if (internalArray[i] == null) {
				print += "null";
			} else {
				print += internalArray[i].toString();
			}
			if (i != internalArray.length - 1) {
				print += ", ";
			}
		}
		return print + "]";
	}

}
