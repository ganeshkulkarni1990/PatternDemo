package policies;

public class DLL<E> {

	DLLNode<E> head;
	DLLNode<E> tail;
	int count = 0;

	public DLL() {
		E e = null;
		this.head = new DLLNode<E>(e);
		this.tail = new DLLNode<E>(e);
		head.next = tail;
		tail.prev = head;
		count = 0;
	}

	public void detachNode(DLLNode<E> node) {
		DLLNode<E> next = node.next;
		DLLNode<E> prev = node.prev;
		prev.next = next;
		next.prev = prev;
		count--;
	}

	public DLLNode<E> addNodeToHead(DLLNode<E> node) {
		DLLNode<E> next = head.next;
		head.next = node;
		node.prev = head;
		node.next = next;
		next.prev = node;
		count++;
		return node;
	}

	public DLLNode<E> addNodeToTail(DLLNode<E> node) {
		DLLNode<E> prev = tail.prev;
		prev.next = node;
		node.prev = prev;
		node.next = tail;
		count++;
		return node;
	}

	public int getSize() {
		return count++;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		DLLNode<E> h = head;
		while (h != null) {
			sb.append(h.toString());
			h = h.next;
		}
		return sb.toString();
	}
}
