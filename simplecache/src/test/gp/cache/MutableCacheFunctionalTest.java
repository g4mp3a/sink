package gp.cache;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class MutableCacheFunctionalTest {

	MutableEntityTagCache<Integer, List<String>> mutableCache = new MutableEntityTagCache<>();

	@Before
	public void setupCache() {
		CacheInitializer.initialize(mutableCache);
	}

	@Test
	public void basicOps() {
		Assert.assertEquals(Arrays.asList("xyz", "bar"), mutableCache.getTags(4));
		Assert.assertEquals(Arrays.asList("foo", "bar"), mutableCache.getTags(1));
		Assert.assertEquals(Arrays.asList("abc", "foo"), mutableCache.getTags(2));
		Assert.assertEquals(Collections.EMPTY_LIST, mutableCache.getTags(3));
		Assert.assertEquals(null, mutableCache.getTags(5));
	}

	@Test
	public void updateExistingEntry() {
		mutableCache.updateTags(3, Arrays.asList("ham", "pork"));
		Assert.assertEquals(Arrays.asList("ham", "pork"), mutableCache.getTags(3));

		mutableCache.updateTags(1, Arrays.asList("hoho", "gogo"));
		Assert.assertEquals(Arrays.asList("hoho", "gogo"), mutableCache.getTags(1));

		mutableCache.updateTags(2, Collections.<String> emptyList());
		Assert.assertEquals(Collections.EMPTY_LIST, mutableCache.getTags(2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNonExistingEntry() {
		mutableCache.updateTags(5, Arrays.asList("jam", "pam"));
	}
}
