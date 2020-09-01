package gp.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MutableCacheConcurrentPerformance {

	private CacheManager cacheManager;
	public Cache ehcache;
	private MutableEntityTagCache<Integer, List<String>> cache;

	@Before
	public void setupCache() {
		cacheManager = CacheManager.newInstance("ehcache.xml");
		ehcache = cacheManager.getCache("sampleCache2");
		cache = new MutableEntityTagCache<Integer, List<String>>();
	}

	@After
	public void clear() {
		cacheManager.shutdown();
	}

	@Test
	public void testConcurrentWrites() throws ExecutionException, InterruptedException {
		System.out.println("Test 100 Thread Concurrency and 10,000 Puts");
		System.out.println("==============================================");
		ExecutorService executorService = Executors.newFixedThreadPool(100);

		List<Future<Long>> futures = new ArrayList<Future<Long>>();
		for (int i = 0; i < 1000; i++) {
			Future<Long> futureDuration = executorService.submit(new EhcacheWriteHammer(ehcache));
			futures.add(futureDuration);
		}
		long totalDuration = 0;
		for (Future<Long> future : futures) {
			totalDuration += future.get();
		}
		System.out.println("Ehcache (ms): " + totalDuration);

		futures = new ArrayList<Future<Long>>();
		for (int i = 0; i < 1000; i++) {
			Future<Long> futureDuration = executorService.submit(new EntityTagCacheWriteHammer(cache));
			futures.add(futureDuration);
		}
		totalDuration = 0;
		for (Future<Long> future : futures) {
			totalDuration += future.get();
		}
		System.out.println("MutableEntityTagCache (ms): " + totalDuration);
		System.out.println();
	}

	@Test
	public void testConcurrentReads() throws ExecutionException, InterruptedException {
		testConcurrentWrites();
		System.out.println("Test 100 Thread Concurrency and 10,000 Gets");
		System.out.println("==============================================");
		ExecutorService executorService = Executors.newFixedThreadPool(100);

		List<Future<Long>> futures = new ArrayList<Future<Long>>();
		for (int i = 0; i < 1000; i++) {
			Future<Long> futureDuration = executorService.submit(new EntityTagCacheReadHammer(cache));
			futures.add(futureDuration);
		}
		long totalDuration = 0;
		for (Future<Long> future : futures) {
			totalDuration += future.get();
		}
		System.out.println("Ehcache (ms): " + totalDuration);

		futures = new ArrayList<Future<Long>>();
		for (int i = 0; i < 1000; i++) {
			Future<Long> futureDuration = executorService.submit(new EntityTagCacheReadHammer(cache));
			futures.add(futureDuration);
		}
		totalDuration = 0;
		for (Future<Long> future : futures) {
			totalDuration += future.get();
		}
		System.out.println("MutableEntityTagCache (ms): " + totalDuration);
		System.out.println();
	}

}
