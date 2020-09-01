package gp.random;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

class NullSkippingIterator<E> implements Iterator<E> {
  
  private Iterator< E> iterator;
  private  E nextItem = null;
  
  public NullSkippingIterator ( E itr) {
    iterator = itr;
  }
  
  public boolean hasNext() {
    
    while (nextItem == null) {

      if (!iterator.hasNext()) return false;
      nextItem = iterator.next();
    }
    return nextItem != null;
  }
  
  public  E next() {
    
    if (hasNext()) {
      E retVal = nextItem;
      nextItem = null;
      return retVal;
    } else {
      throw new NoSuchElementException();
    }
  }
  
  public void remove() {
    iterator.remove();
  }
}

public class NullSkippingIteratorSolution {
  
  @Test
  void test(){}
}