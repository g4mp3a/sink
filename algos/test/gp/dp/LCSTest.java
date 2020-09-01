package gp.dp;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LCSTest {
  LCS lcs=new LCS();

  @Test
  public void lengthTestABCBDABandEmpty() {
    assertEquals(0,new LCS("ABCBDAB",""));
  }

  @Test
  public void lengthTestABCBDABandBlank() {
    assertEquals(0,new LCS("ABCBDAB"," "));
  }

  @Test
  public void lengthTestABCBDABandNull() {
    assertEquals(0,new LCS("ABCBDAB",null).getLength());
  }

  @Test
  public void lengthTestABCBDABandBDCABC() {
    assertEquals(4,new LCS("ABCBDAB","BDCABC").getLength());
  }

  @Test
  public void lengthTestABCDGHandADH() {
    assertEquals(3,new LCS("ABCBDAB","ADH").getLength());
  }

  @Test
  public void lengthTestAGGTABandGXTXAYB() {
    assertEquals(4,new LCS("ABCBDAB","GXTXAYB").getLength());
  }

  @Test
  public void lcsTestABCBDABandEmpty() {
    assertEquals(new String(),new LCS("ABCBDAB","").getLongestCommonSubSequence());

  }

  @Test
  public void lcsTestABCBDABandBlank() {
    assertEquals("",new LCS("ABCBDAB"," ").getLongestCommonSubSequence());
  }

  @Test
  public void lcsTestABCBDABandNull() {
    assertEquals("",new LCS("ABCBDAB",null).getLongestCommonSubSequence());
  }

  @Test
  public void lcsTestABCBABandBDCABC() {
    assertEquals("BCAB",new LCS("ABCBDAB","BDCAB").getLongestCommonSubSequence());
  }

  @Test
  public void lcsTestABCDGHandADH() {
    assertEquals("ADH",new LCS("ABCBDAB","ADH").getLongestCommonSubSequence());
  }

  @Test
  public void lcsTestAGGTABandGXTXAYB() {
    assertEquals("GTAB",new LCS("ABCBDAB","GXTXAYB").getLongestCommonSubSequence());
  }

  @Test
  public void allLcsTestABCBABandBDCABC() {
    for (final String s : new LCS("ABCBDAB","BDCABC").getAllLongestCommonSequences()) {
      assertTrue("BCAB".equals(s)||"BDAB".equals(s));
    }
  }

  @Test
  public void acsTestABCDBABandBDCABC() {
    System.out.printf("acs(ABCDBAB, BDCABC) = %d\n",new LCS("ABCBDAB","BDCABC").getNumberOfACSs());
  }

  @Test
  public void acsTestABCandEFG() {
    System.out.printf("acs(ABC, EFG) = %d\n",new LCS("ABC","EFG").getNumberOfACSs());
  }

  @Test
  public void acsTestABCandABC() {
    System.out.printf("acs(ABC, ABC) = %d\n",new LCS("ABC","ABC").getNumberOfACSs());
  }
}
