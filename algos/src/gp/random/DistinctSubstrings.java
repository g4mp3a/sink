package gp.random;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DistinctSubstrings {

  public static Set<String> distinctSubstring(String str) {
    Set<String> result=new HashSet<String>();

    for (int i=0; i<str.length(); i++) {
      for (int j=i+1; j<=str.length(); j++) {
        result.add(str.substring(i,j));
      }
    }

    // Traverse through the given String and
    // one by one generate subStrings beginning
    // from s[i].
    // for (int i = 0; i < s.length(); ++i) {
    // // One by one generate subStrings ending
    // // with s[j]
    // String ss = "";
    // for (int j = i; j < s.length(); ++j) {
    // ss = ss + s.charAt(j);
    // result.add(ss);
    // }
    // }
    return result;
  }

  // Driver Code
  public static void main(String[] args) {
    String str="aaaa";
    Set<String> subs=distinctSubstring(str);
    System.out.println("Number of Distinct Substrings is: "+subs.size());
    System.out.println("Distinct Substrings are: ");
    for (String s : subs) {
      System.out.println(s);
    }
  }
}
