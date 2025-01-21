package policies;

public interface EvictionPolicy<K> {
	void keyAccessed(K k);
	K evict();
}
