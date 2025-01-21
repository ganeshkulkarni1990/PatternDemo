package policies;

import java.util.Hashtable;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {
	private Hashtable<K, DLLNode<K>> map;
	private DLL<K> dll;

	public LRUEvictionPolicy() {
		this.map = new Hashtable<K, DLLNode<K>>();
		this.dll = new DLL<>();
	}

	@Override
	public void keyAccessed(K k) {
		if (map.containsKey(k)) {
			DLLNode<K> node = map.get(k);
			dll.detachNode(node);
			dll.addNodeToHead(node);
		} else {
			map.put(k, dll.addNodeToHead(new DLLNode<>(k)));
		}
	}

	@Override
	public K evict() {
		DLLNode<K> e = dll.tail;
		DLLNode<K> prev = e.prev;
		dll.detachNode(prev);
		map.remove(prev.e);
		return prev.e;
	}

}
