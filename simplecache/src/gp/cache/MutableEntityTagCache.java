package gp.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * A very simple cache using java.util.concurrent.
 */
public class MutableEntityTagCache<K, V> extends AbstractEntityTagCache<K, V> {

	public MutableEntityTagCache() {
		super(new ConcurrentHashMap<K, CacheEntry<V>>());
	}

	/**
	 * Updates an existing entry in the cache. Does
	 * 
	 * @param key
	 *            key of the entry that is used as handle, to retrieve at a
	 *            later point of time or to delete the entry
	 * @param value
	 *            The actual object that is to be cached.
	 * @throws IllegalArgumentException
	 *             Throws illegal argument exception, if the key or value is
	 *             null; also when the key does not exist in the cache
	 */
	@Override
	public void updateTags(K key, V value) {
		boolean exists = cache.containsKey(key);
		if (!exists) { throw new IllegalArgumentException("Invalid Key."); }
		put(key, value);
	}
}
