package gp.cache;

import java.util.Arrays;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReadOnlyCachePerformanceTest {

	private CacheManager cacheManager;
	public Cache ehcache;
	private ReadOnlyEntityTagCache<Integer, List<String>> cache;

	@Before
	public void setupCache() {
		cacheManager = CacheManager.newInstance("ehcache.xml");
		ehcache = cacheManager.getCache("sampleCache2");
		cache = new ReadOnlyEntityTagCache<>();
	}

	@After
	public void clear() {
		cacheManager.shutdown();
	}

	@Test
	public void test10000WritesAndReads() {
		System.out.println("Test 10,000 entries");
		System.out.println("===================");
		doWritesAndReads(10 * 1000);
	}

	@Test
	public void test100000WritesAndReads() {
		System.out.println("Test 100,000 entries");
		System.out.println("===================");
		doWritesAndReads(100 * 1000);
	}

	@Test
	public void test1000000WritesAndReads() {
		System.out.println("Test 1,000,000 entries");
		System.out.println("===================");
		doWritesAndReads(1 * 1000 * 1000);
	}

	private void doWritesAndReads(int times) {
		System.out.println("Write performance");
		long start = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			ehcache.put(new Element(i, Arrays.asList("value1" + i, "value2" + i)));
		}
		long duration = System.currentTimeMillis() - start;
		System.out.println("Ehcache (ms): " + duration);

		start = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			cache.put(i, Arrays.asList("value1" + i, "value2" + i));
		}
		duration = System.currentTimeMillis() - start;
		System.out.println("ReadOnlyEntityTagCache (ms): " + duration);

		System.out.println("***********");

		System.out.println("Read performance");
		start = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			ehcache.get(i);
		}
		duration = System.currentTimeMillis() - start;
		System.out.println("Ehcache (ms): " + duration);

		start = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			cache.getTags(i);
		}
		duration = System.currentTimeMillis() - start;
		System.out.println("ReadOnlyEntityTagCache (ms): " + duration);

		System.out.println();
	}

}
