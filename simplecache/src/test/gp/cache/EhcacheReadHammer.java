package gp.cache;

import java.util.concurrent.Callable;

import net.sf.ehcache.Cache;

public class EhcacheReadHammer implements Callable<Long> {

	private Cache cache;

	public EhcacheReadHammer(Cache cache) {
		this.cache = cache;
	}

	public Long call() throws Exception {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10 * 1000; i++) {
			cache.get(i);
		}
		return System.currentTimeMillis() - start;
	}
}
