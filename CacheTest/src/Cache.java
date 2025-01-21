import exceptions.KeyNotFoundException;
import exceptions.StorageFullException;
import policies.EvictionPolicy;
import policies.LRUEvictionPolicy;
import storage.HashedStorage;
import storage.Storage;

public class Cache<K, V> {
	private Storage<K, V> map;
	private EvictionPolicy<K> evictionPolicy;
	int capacity;

	public Cache(int capacity) {
		this.map = new HashedStorage<K, V>(capacity);
		this.evictionPolicy = new LRUEvictionPolicy<K>();
		this.capacity = capacity;
	}

	public synchronized void put(K k, V v) {
		System.out.println("Got Request to put: " + k + ": " + v);
		try {
			map.put(k, v);
			evictionPolicy.keyAccessed(k);
		} catch (StorageFullException e) {
			K evictedItem = evictionPolicy.evict();
			System.out.println("Cache is full...Evicting item: " + evictedItem);
			map.remove(evictedItem);
			map.put(k, v);
			evictionPolicy.keyAccessed(k);
		}
	}

	public synchronized V get(K k) {
		System.out.println("Got Request to get: " + k);
		try {
			V v = map.get(k);
			evictionPolicy.keyAccessed(k);
			return v;
		} catch (KeyNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void print() {
		System.out.println(map);
	}

}
