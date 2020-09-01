package gp.sums;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class NumbersInAnArrayWithAGivenSumTest {
  private NumbersInAnArrayWithAGivenSum classUnderTest=new NumbersInAnArrayWithAGivenSum();

  @Test
  public void testFindTwoNumbersInASortedArraySum15() {
    assertTrue(classUnderTest.setA(new int[] { 1, 2, 4, 7, 11, 15 }).findTwoNumbersInASortedArrayWithGivenSum(15));
  }

  @Test
  public void testFindTwoNumbersInASortedArraySum10() {
    assertTrue(!classUnderTest.setA(new int[] { 1, 2, 4, 7, 11, 15 }).findTwoNumbersInASortedArrayWithGivenSum(10));
  }

  @Test
  public void testFindThreeNumbersInASortedArraySum15() {
    assertTrue(!classUnderTest.setA(new int[] { 1, 2, 4, 7, 11, 15 }).findThreeNumbersInASortedArrayWithGivenSum(15));
  }

  @Test
  public void testFindThreeNumbersInASortedArraySum10() {
    assertTrue(classUnderTest.setA(new int[] { 1, 2, 4, 7, 11, 15 }).findThreeNumbersInASortedArrayWithGivenSum(10));
  }

}
