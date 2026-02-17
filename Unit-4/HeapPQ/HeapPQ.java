
public class HeapPQ<E extends Comparable<E>> implements MyPriorityQueue<E> {

	private E[] heap;
	private int objectCount;

	@SuppressWarnings("unchecked")
	public HeapPQ() {
		this.heap = (E[]) new Comparable[3];
		this.objectCount = 0;
	}

	// Returns the number of elements in the priority queue
	public int size() {
		return objectCount;
	}

	// DO NOT CHANGE MY JANKY TOSTRING!!!!!
	public String toString() {
		StringBuffer stringbuf = new StringBuffer("[");
		for (int i = 0; i < objectCount; i++) {
			stringbuf.append(heap[i]);
			if (i < objectCount - 1)
				stringbuf.append(", ");
		}
		stringbuf.append("]\nor alternatively,\n");

		for (int rowLength = 1, j = 0; j < objectCount; rowLength *= 2) {
			for (int i = 0; i < rowLength && j < objectCount; i++, j++) {
				stringbuf.append(heap[j] + " ");
			}
			stringbuf.append("\n");
			if (j < objectCount) {
				for (int i = 0; i < Math.min(objectCount - j, rowLength * 2); i++) {
					if (i % 2 == 0)
						stringbuf.append("/");
					else
						stringbuf.append("\\ ");
				}
				stringbuf.append("\n");
			}
		}
		return stringbuf.toString();
	}

	// Doubles the size of the heap array
	@SuppressWarnings("unchecked")
	private void increaseCapacity() {
		E[] temp = (E[]) new Comparable[heap.length * 2];
		for (int i = 0; i < heap.length; i++) {
			temp[i] = heap[i];
		}
		heap = temp;
	}

	// Returns the index of the "parent" of index i
	private int parent(int i) {
		return (i - 1) / 2 >= 0 ? (i - 1) / 2 : -1;
	}

	// Returns the index of the *smaller child* of index i
	private int smallerChild(int i) {
		int left = i * 2 + 1, right = i * 2 + 2;
		try {
			heap[left].equals(' ');
		} catch (Exception e) {
			return -1;
		}
		try {
			heap[right].equals(' ');
		} catch (Exception e) {
			return left;
		}
		return heap[left].compareTo(heap[right]) < 0 ? left : right;
	}

	// Swaps the contents of indices i and j
	private void swap(int i, int j) {
		E temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}

	// Bubbles the element at index i upwards until the heap properties hold again.
	private void bubbleUp(int i) {
		int parent = parent(i);
		if (parent == -1) {
			return;
		}
		while (heap[i].compareTo(heap[parent]) < 0) {
			swap(i, parent);
			i = parent;
			parent = parent(i);
			if (parent == -1) {
				break;
			}
		}
	}

	// Bubbles the element at index i downwards until the heap properties hold
	// again.
	private void bubbleDown(int i) {
		int smallerChild = smallerChild(i);
		if (smallerChild == -1) {
			return;
		}
		while (heap[i].compareTo(heap[smallerChild]) > 0) {
			swap(i, smallerChild);
			i = smallerChild;
			smallerChild = smallerChild(i);
			if (smallerChild == -1) {
				break;
			}
		}
	}

	@Override
	public void add(E obj) {
		if (objectCount == heap.length - 1)
			increaseCapacity();
		heap[objectCount++] = obj;
		bubbleUp(objectCount - 1);
	}

	@Override
	public E removeMin() {
		if (objectCount == 1) {
			objectCount--;
			E last = heap[0];
			heap[0] = null;
			return last;
		}
		swap(0, objectCount - 1);
		E removed = heap[objectCount - 1];
		heap[objectCount - 1] = null;
		objectCount--;
		bubbleDown(0);
		return removed;
	}

	@Override
	public E peek() {
		return heap[0];
	}

	@Override
	public boolean isEmpty() {
		return heap[0] == null;
	}

}
