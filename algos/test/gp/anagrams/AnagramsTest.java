package gp.anagrams;

import org.junit.Test;
import java.io.File;

public class AnagramsTest {

  @Test
  public void test() {
    Anagrams.getAnagramGroups(new File("resources/dictionary.txt"),8);
  }

}
