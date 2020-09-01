package gp.cache;

import java.util.HashMap;

public class ReadOnlyEntityTagCache<K, V> extends AbstractEntityTagCache<K, V> {

	public ReadOnlyEntityTagCache() {
		super(new HashMap<K, CacheEntry<V>>());
	}

	/**
	 * Update operation is not allowed. Invocation results in
	 * UnsupportedOperationException
	 * 
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void updateTags(K key, V value) {
		throw new UnsupportedOperationException("Updates not allowed.");
	}
}
