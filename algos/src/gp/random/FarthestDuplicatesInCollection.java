package gp.random;

class FarthestDuplicatesInCollectionSolution {
  
  static int farthestDuplicates(int[] a) {
    
    if (a.length == 0) return 0;
    
    int result=0;
    Map m = new HashMap();
    for (int i=0; i < a.length; i++) {
      
      if (!m.containsKey(a[i])) { m.put(a[i], i); continue; }
      
      if (result < i - m.get(a[i])) result = i - m.get(a[i]);
    }
    
    return result;
  }
}
