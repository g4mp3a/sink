package gp.cache;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class EntityTagCacheWriteHammer implements Callable<Long> {

	private AbstractEntityTagCache<Integer, List<String>> cache;

	public EntityTagCacheWriteHammer(AbstractEntityTagCache<Integer, List<String>> cache) {
		this.cache = cache;
	}

	public Long call() throws Exception {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10 * 1000; i++) {
			cache.put(i, Arrays.asList("value" + i));
		}
		return System.currentTimeMillis() - start;
	}
}
