import java.util.EmptyStackException;

public class MyStack<E> {
	private ListNode<E> head;

	public MyStack() {
		this.head = null;
	}

	public ListNode<E> getHead() {
		return head;
	}

	public boolean empty() {
		return head == null;
	}

	public E push(E obj) {
		head = new ListNode<E>(obj, head);
		return obj;
	}

	public E pop() {
		if (head == null)
			throw new EmptyStackException();
		ListNode<E> temp = head;
		head = head.getNext();
		return temp.getValue();
	}

	public E peek() {
		if (head == null)
			throw new EmptyStackException();
		return head.getValue();
	}
}
