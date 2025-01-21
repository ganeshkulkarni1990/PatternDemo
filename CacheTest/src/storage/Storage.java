package storage;

public interface Storage<K,V> {
	V get(K k);
	void put(K k, V v);
	V remove(K k);
}
