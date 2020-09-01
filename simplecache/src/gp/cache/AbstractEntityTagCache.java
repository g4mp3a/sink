package gp.cache;

import java.util.Map;

public abstract class AbstractEntityTagCache<K, V> implements EntityTagCache<K, V> {

	protected Map<K, CacheEntry<V>> cache;

	public AbstractEntityTagCache(Map<K, CacheEntry<V>> map) {
		this.cache = map;
	}

	/**
	 * Retrieves the tags associated with the supplied key from the cache
	 * that has been cached before using {@link #put(Object, Object)}
	 * 
	 * @param key
	 *            handle of the entry with which the tags has been cached.
	 * @return Returns the cached tags. Returns null, if there is no entry with
	 *         the key
	 */
	@Override
	public V getTags(K key) {
		if (key == null) { throw new IllegalArgumentException("Invalid Key."); }

		CacheEntry<V> entry = cache.get(key);

		if (entry == null) { return null; }

		return entry.getEntry();
	}

	/**
	 * Inserts an entry into cache
	 * 
	 * @param key
	 *            key of the entry that is used as handle, to retrieve at a
	 *            later point of time or to delete the entry
	 * @param value
	 *            The actual object that is to be cached.
	 * @throws IllegalArgumentException
	 *             Throws illegal argument exception, if the key or value is
	 *             null.
	 */
	protected void put(K key, V value) {
		if (key == null) { throw new IllegalArgumentException("Invalid Key."); }
		if (value == null) { throw new IllegalArgumentException("Invalid Value."); }

		cache.put(key, new CacheEntry<V>(value));
	}
}
