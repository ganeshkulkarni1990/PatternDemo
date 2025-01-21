package storage;

import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;

import exceptions.KeyNotFoundException;
import exceptions.StorageFullException;

public class HashedStorage<K, V> implements Storage<K, V> {

	private int capacity;
	private Hashtable<K, V> hashtable;
	private final AtomicInteger counter = new AtomicInteger(0);

	public HashedStorage(int capacity) {
		this.capacity = capacity;
		this.hashtable = new Hashtable<>();
	}

	@Override
	public V get(K k) {
		if (hashtable.containsKey(k)) {
			return hashtable.get(k);
		} else {
			throw new KeyNotFoundException("Key Not Found:" + k);
		}
	}

	@Override
	public void put(K k, V v) {
		if (hashtable.containsKey(k)) {
			hashtable.put(k, v);
		} else if (counter.get() < capacity) {
			hashtable.put(k, v);
			counter.incrementAndGet();
		} else {
			throw new StorageFullException("Storage Full...");
		}
	}

	public int getSize() {
		return counter.get();
	}

	@Override
	public V remove(K k) {
		if (hashtable.containsKey(k)) {
			counter.decrementAndGet();
			return hashtable.remove(k);
		}
		throw new KeyNotFoundException("Key Not Fount In Cache");
	}

	public String toString() {
		return hashtable.toString();
	}
}
