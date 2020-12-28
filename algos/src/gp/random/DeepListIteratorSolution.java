package gp.random;


interface NestedInteger {
  
  boolean isInteger();
  
  Integer getInteger();
  
  List<NestedInteger> getList();
  
}

class DeepListIterator {
  
  Stack<NestedInteger> stack = new Stack<NestedInteger>();
  
  public DeepListIterator(List<NestedInteger> nestedList) {
    
    if (nestedList == null) return;
    
    for (int i = nestedList.size()-1; i>=0; i--) {
      stack.push(nestedList.get(i));
    }
  }
  
  public next() {
    stack.pop().getInteger();
  }
  
  public hasNext() {

    while (!stack.isEmpty()) {
      
      NestedInteger top = stack.peek();
      
      if (top.isInteger()) return true;
      
      for (int i = top.getList().size()-1; i>=0; i--)) {
        stack.push(top.getList().get(i));
      }      
      stack.pop();
    }

    return false;
  }
  
}

class DeepListIteratorSolution {
  
}