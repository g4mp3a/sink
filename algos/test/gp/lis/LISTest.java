package gp.lis;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LISTest {
  LIS lis=new LIS();

  @Test
  public void testLIS() {
    assertEquals(6,lis.setA(new int[] { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 }).getLengthOfLIS());
  }
}
