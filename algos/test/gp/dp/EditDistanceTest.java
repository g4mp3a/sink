package gp.dp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EditDistanceTest {
  EditDistance ed=new EditDistance();

  @Test
  public void testLevDistKittenAndSitting() {
    assertEquals(3,ed.setSrc("Kitten").setDest("Sitting").getLevenshteinDistance());
    assertEquals(3,ed.setSrc("Sitting").setDest("Kitten").getLevenshteinDistance());
    assertEquals(0,ed.setSrc("Sitting").setDest("Sitting").getLevenshteinDistance());
    assertEquals(6,ed.setSrc("Kitten").setDest(" ").getLevenshteinDistance());
    assertEquals(0,ed.setSrc(null).setDest(null).getLevenshteinDistance());
  }

  @Test
  public void testLCSDistKittenAndSitting() {
    assertEquals(5,ed.setSrc("Kitten").setDest("Sitting").getLCSDistance());
    assertEquals(5,ed.setSrc("Sitting").setDest("Kitten").getLCSDistance());
    assertEquals(7,ed.setSrc("Sitting").setDest("").getLCSDistance());
  }

  @Test
  public void testHammingDistance() {
    assertEquals(3,ed.setSrc("karolin").setDest("kathrin").getHammingDistance());
    assertEquals(2,ed.getHammingDistance("1011101","1001001"));
    assertEquals(3,ed.setSrc("2173896").setDest("2233796").getHammingDistance());
    assertEquals(1,ed.getHammingDistance(0,1));
    assertEquals(2,ed.getHammingDistance(2,2*2));
    assertEquals(2,ed.getHammingDistance(2*2*2*2,2*2*2*2*2*2*2*2*2));
    assertEquals(4,ed.getHammingDistance(8,7));
    assertEquals(1,ed.getHammingDistance(7,6));
    assertEquals(0,ed.setSrc("karolin").setDest("karolin").getHammingDistance());
    assertEquals(0,ed.getHammingDistance(1,1));
    assertEquals(-1,ed.getHammingDistance("10111011","1001001"));
  }
}
