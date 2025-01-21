package policies;

public class DLLNode<E> {
	E e;
	DLLNode<E> next;
	DLLNode<E> prev;
	public DLLNode(E e) {
		this.e = e;
		next = null;
		prev = null;
	}
	public String toString() {
		return " " + e + " ";
	}
}
