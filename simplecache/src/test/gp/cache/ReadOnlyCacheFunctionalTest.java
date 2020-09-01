package gp.cache;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ReadOnlyCacheFunctionalTest {

	ReadOnlyEntityTagCache<Integer, List<String>> readOnlyCache = new ReadOnlyEntityTagCache<>();

	@Before
	public void setupCache() {
		CacheInitializer.initialize(readOnlyCache);
	}

	@Test
	public void basicOps() {
		Assert.assertEquals(Arrays.asList("xyz", "bar"), readOnlyCache.getTags(4));
		Assert.assertEquals(Arrays.asList("foo", "bar"), readOnlyCache.getTags(1));
		Assert.assertEquals(Arrays.asList("abc", "foo"), readOnlyCache.getTags(2));
		Assert.assertEquals(Collections.EMPTY_LIST, readOnlyCache.getTags(3));
		Assert.assertEquals(null, readOnlyCache.getTags(5));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void noUpdates() {
		readOnlyCache.updateTags(5, Arrays.asList("hoho", "gogo"));
	}
}
