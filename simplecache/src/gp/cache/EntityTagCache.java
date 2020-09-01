package gp.cache;

public interface EntityTagCache<K, V> {

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
	void updateTags(K key, V value);

	/**
	 * Retrieves the tags associated with the supplied key from the cache
	 * that has been cached before using {@link #put(Object, Object)}
	 * 
	 * @param key
	 *            handle of the entry with which the tags has been cached.
	 * @return Returns the cached tags. Returns null, if there is no entry with
	 *         the key K.
	 */
	V getTags(K key);
}
