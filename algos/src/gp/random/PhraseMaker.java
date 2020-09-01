package gp.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Only works if the string only contains words in the dictionary. Also, when
 * finding out the number of words and the words themselves, results will vary
 * from run to run in the presence of combo words.
 *
 * @author gautampriya
 */
class PhraseMaker {

  /**
   * Recursion
   */
  public static boolean containsPhrase(final String s, final Set<String> dict) {
    return containsPhraseHelper(s,dict,0);
  }

  public static boolean containsPhraseHelper(final String s, final Set<String> dict, final int startIdx) {
    if (startIdx==s.length())
      return true;

    for (final String w : dict) {
      final int endIdx=startIdx+w.length();

      if (endIdx>s.length()) {
        continue;
      }

      if (s.substring(startIdx,endIdx).equals(w)) {
        if (containsPhraseHelper(s,dict,endIdx))
          return true;
      }
    }
    return false;
  }

  /**
   * Using DP Use an an array a[] such that a[i] = true if [0...i-1] can be
   * segmented using dictionary Initial state a[0] = true
   *
   * Runs in sizeofinput*sizeofdict. Draw back of this implementation is a large
   * dict.
   */
  public static boolean containsPhraseDP(final String s, final Set<String> dict) {
    final boolean[] a=new boolean[s.length()+1];
    a[0]=true;

    for (int startIdx=0; startIdx<s.length(); ++startIdx) {
      if (a[s.length()])
        return true;
      // [0...i-1] is not segmentable, keep looking
      if (!a[startIdx]) {
        continue;
      }

      // if [0...i-1] is segmentable,
      // then check if the suffix [i...s.length()] is also segmentable
      for (final String w : dict) {
        if (a[s.length()])
          return true;
        final int endIdx=startIdx+w.length();
        if (endIdx>s.length()) {
          continue;
        }
        if (a[endIdx]) {
          continue;
        }
        if (s.substring(startIdx,endIdx).equals(w)) {
          a[endIdx]=true;
        }
      }
    }
    return a[s.length()];
  }

  /**
   * Implementation that runs in sizeofinput*sizeofinput time assuming dict
   * 'contains' operation is not more expensive.
   */
  public static boolean efficientContainsPhraseDP(final String s, final Set<String> dict) {
    final int[] a=new int[s.length()+1];
    Arrays.fill(a,-1);
    a[0]=0;

    for (int startIdx=0; startIdx<s.length(); ++startIdx) {
      if (a[startIdx]!=-1) {
        for (int endIdx=startIdx+1; endIdx<=s.length(); ++endIdx) {
          if (dict.contains(s.substring(startIdx,endIdx))) {
            a[endIdx]=startIdx;
          }
        }
      }
    }
    return a[s.length()]!=-1;
  }

  public static List<String> getPhrases(String s, Set<String> dict) {
    List<String>[] a=new ArrayList[s.length()+1];
    a[0]=new ArrayList<>();

    for (int i=0; i<s.length(); i++) {
      if (a[i]!=null) {
        for (int j=i+1; j<=s.length(); j++) {
          if (dict.contains(s.substring(i,j))) {
            if (a[i]==null) {
              List<String> l=new ArrayList<>();
              a[i]=l;
            }
            a[i].add(s.substring(i,j));
          }
        }
      }
    }

    if (a[s.length()]==null)
      return new ArrayList<>();
    List<String> result=new ArrayList<>();
    dfs(a,result,"",s.length());
    return result;

  }

  private static void dfs(List<String>[] a, List<String> result, String curr, int i) {
    if (i==0) {
      result.add(curr.trim());
      return;
    }
    for (String s : a[i]) {
      String combined=s+" "+curr;
      dfs(a,result,combined,i-s.length());
    }
  }
}
