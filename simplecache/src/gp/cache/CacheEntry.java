package gp.cache;

class CacheEntry<V> {

	private V entry;

	public CacheEntry(V entry) {
		this.entry = entry;
	}

	/**
	 * @return the entry
	 */
	public V getEntry() {
		return entry;
	}
}