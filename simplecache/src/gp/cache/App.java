package com.simple.cache;

import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		ReadOnlyEntityTagCache<Integer, List<String>> readOnlyCache = new ReadOnlyEntityTagCache<>();
		CacheInitializer.initialize(readOnlyCache);
		System.out.println("Read-only cache values");
		System.out.println("4: " + readOnlyCache.getTags(4));
		System.out.println("1: " + readOnlyCache.getTags(1));
		System.out.println("3: " + readOnlyCache.getTags(3));
		System.out.println("5: " + readOnlyCache.getTags(5));

		System.out.println();

		MutableEntityTagCache<Integer, List<String>> mutableCache = new MutableEntityTagCache<>();
		CacheInitializer.initialize(mutableCache);
		System.out.println("Mutable cache values");
		System.out.println("4: " + mutableCache.getTags(4));
		System.out.println("1: " + mutableCache.getTags(1));
		System.out.println("3: " + mutableCache.getTags(3));
		System.out.println("5: " + mutableCache.getTags(5));

		System.out.println();

		System.out.println("Mutable cache post update to key 3");
		mutableCache.updateTags(3, Arrays.asList("coco", "bobo"));
		System.out.println("3: " + mutableCache.getTags(3));

		System.out.println();

		System.out.println("Mutable cache post update to the non existant key 5");
		mutableCache.updateTags(5, Arrays.asList("hoho", "gogo"));
		System.out.println("5: " + mutableCache.getTags(5));

	}
}
