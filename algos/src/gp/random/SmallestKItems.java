package gp.random;

class SmallestKItemsSolution {
  
  static void printSmallestKItems(int[] a, int k) {
  
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    
    for (int i=0; i<k; i++) maxHeap.add(a[i]);
    
    for (int i=k; i<a.length; i++) {
      
      int top = maxHeap.peek();
      if (a[i] < top) {
        maxHeap.remove();
        maxHeap.add(a[i]);
      }
    }
    
    maxHeap.forEach(i -> System.out.println(i));
  }
}