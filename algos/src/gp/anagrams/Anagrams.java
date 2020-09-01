package gp.anagrams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Anagrams {
  public static void getAnagramGroups(final File f, final int groupSize) {
    final Map<String, List<String>> anagrams=new HashMap<>();
    try (Scanner s=new Scanner(f)) {
      while (s.hasNext()) {
        final String word=s.next();
        final String wordSignature=alphabetize(word);
        List<String> l=anagrams.get(wordSignature);

        if (l==null) {
          anagrams.put(wordSignature,l=new ArrayList<>());
        }
        l.add(word);

      }
    } catch (final FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    for (final List<String> l : anagrams.values()) {
      if (l.size()>=groupSize) {
        System.out.println(l.size()+":"+l);
      }
    }

  }

  private static String alphabetize(final String s) {
    final char[] c=s.toCharArray();
    Arrays.sort(c);
    return new String(c);
  }

}
