package gp.random;

import org.junit.jupiter.api.Test;

class MaxStack<E implements Comparable> {
  
  private Deque<E> s = new ArrayDeque();
  int E m = s.peek();
  
  public void push(E e) {
    
    if (m == null) {
          m = e;
    }
    if (m != null && m.compareTo(e) < 0) {
      s.push(m);
      m = e;
    } 
    s.push(e);
  }
  
  public E peek() {
    return s.peek();
  }
  
  public E pop() {
    
    E e = s.pop(e);
    if (m.compareTo(e) == 0) {
      m = s.pop();
    }
    return e;
  }
}

public class MaxStackSolution {
  
  @Test
  void test() {
    
  }
}