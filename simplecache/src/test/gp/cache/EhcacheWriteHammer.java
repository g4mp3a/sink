package gp.cache;

import java.util.Arrays;
import java.util.concurrent.Callable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class EhcacheWriteHammer implements Callable<Long> {

	private Cache cache;

	public EhcacheWriteHammer(Cache cache) {
		this.cache = cache;
	}

	public Long call() throws Exception {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10 * 1000; i++) {
			cache.put(new Element(i, Arrays.asList("value" + i)));
		}
		return System.currentTimeMillis() - start;
	}
}
