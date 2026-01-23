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

	public boolean push(E obj) {
		head = new ListNode<E>(obj, head);
		return true;
	}

	public boolean pop() {
		if (head == null) throw new EmptyStackException();
		head = head.getNext();
		return true;
	}

	public E peek() {
		if (head == null) throw new EmptyStackException();
		return head.getValue();
	}
}
