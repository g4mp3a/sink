package gp.random;

import org.junit.jupiter.api.Test;

import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.ConcurrentHashMap;

/**
 * Bounded cache implementation that stores a fixed number of key-value pairs.
 * It allows any number of updates to existing key-value pairs.
 * It provides, O(1) get and put operations.
 * It does not allow null values.
 */
class ConcurrentBoundedCache<K,V> {
  
  private Queue<K> q = new ArrayDeque<>();
  private Map<K,V> m = new ConcurrentHashMap<>();
  private int size;
  private int count=0;
  
  public BoundedCache(int size) {
    
    this.size = size;
  }
  
  public synchronized V get(K k) {
    
    return m.get(k);
  }
  
  public synchronized void put(K k, V v) {

    if (map.get(k) != null) {
      map.put(k,v);
      return;
    }
    
    if (count >= size) {
      K keyToRemove = q.poll();
      m.remove(keyToRemove);
      q.add(k);
      m.put(k,v);
      return;
    }
    
    q.add(k);
    m.put(k,v);
    count++;
    return;
  }
}

public class ConcurrentBoundedCacheSolution {
  
  @Test
  void boundTesting() {
    BoundedCache<Integer, String> cache = new BoundedCache<>();
  }
  
  
}