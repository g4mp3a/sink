package gp.dp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PalindromeTest {
  private Palindrome p=new Palindrome();

  @Test
  public void testABCCBA() {
    assertEquals(true,p.setS("ABCCBA").isPalindromeUsingStringBuilder());
    assertEquals(true,p.setS("ABCCBA").isPalindrome());
  }

  @Test
  public void testABCABC() {
    assertEquals(false,p.setS("ABCABC").isPalindromeUsingStringBuilder());
    assertEquals(false,p.setS("ABCABC").isPalindrome());
  }

  @Test
  public void testNumberOfInsertionsForAb3bd() {
    assertEquals(2,p.setS("Ab3bd").minNumberOfInsertionsNeededToMakeAPalindrome());
  }

  @Test
  public void testNumberOfInsertionsForABC() {
    assertEquals(2,p.setS("ABC").minNumberOfInsertionsNeededToMakeAPalindrome());
  }

  @Test
  public void testNumberOfInsertionsForEmptyAndNull() {
    assertEquals(0,p.setS("").minNumberOfInsertionsNeededToMakeAPalindrome());
    assertEquals(0,p.setS(" ").minNumberOfInsertionsNeededToMakeAPalindrome());
    assertEquals(0,p.setS(null).minNumberOfInsertionsNeededToMakeAPalindrome());
  }

  @Test
  public void testNumberOfInsertionsForABA() {
    assertEquals(0,p.setS("ABA").minNumberOfInsertionsNeededToMakeAPalindrome());
  }
}
