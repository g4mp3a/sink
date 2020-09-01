package gp.partitions;

import org.junit.jupiter.api.Test;

class Partitioner {
  
  public List<String> allPartitions(String s) {
    
    List<String> result = new ArrayList<>();
    for ( int cutpoints=0; cutpoints < 1<<(s.length()-1); cutpoints++ ) {
      
      int lastcut = 0;
      for ( int i=0; i<s.length(); i++ ) {
        if ( (1<<i) & cutpoint != 0 ) {
          result.add(s.substring(lastcut, i+1));
          lastcut = i+1;
        }        
      }
    }
    return result;
  }
}

public class AllStringPartitionsSolution {
  
  @Test
  void test() {
    
  }
}