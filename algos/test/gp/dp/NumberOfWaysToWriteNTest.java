package gp.dp;

import org.junit.Test;

/**
 * @author gautampriya
 * 
 */
public class NumberOfWaysToWriteNTest {

  private final NumberOfWaysToWriteN numberOfWaysToWriteN=new NumberOfWaysToWriteN();

  @Test
  public void test() {
    for (int i=0; i<100; i+=5) {
      long startTime=System.nanoTime();
      System.out.printf("Number of ways to write %d = %d \n",i,numberOfWaysToWriteN.setN(i).get());
      System.out.printf("Execution time for %d = %d \n",i,System.nanoTime()-startTime);
    }
  }
}
