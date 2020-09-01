package gp.dp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BinaryNumbersTest {

  BinaryNumbers bn=new BinaryNumbers();

  @Test
  public void testGetSum01101And10111() {
    assertEquals("100100",bn.setB1("01101").setB2("10111").getSum());
  }

  @Test
  public void testGetSum01101And1110111() {
    assertEquals("10000100",bn.setB1("01101").setB2("1110111").getSum());
  }

  @Test
  public void testGetSum1110111And01101() {
    assertEquals("10000100",bn.setB1("1110111").setB2("01101").getSum());
  }
}
