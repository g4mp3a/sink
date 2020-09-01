package gp.random;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ComplementingPairs {

  public static boolean hasPairWithSum(int n, int[] a) {
    Arrays.sort(a);
    int l=0, r=a.length;
    while (l<r) {
      int sum=a[l]+a[r];
      if (sum==n) return true;
      if (sum<n) l++;
      else r--;
    }
    return false;
  }

  public static boolean hasPairWithSumEfficient(int n, int[] a) {
    Set<Integer> compliments=new HashSet<>();

    for (int i=0; i<a.length; i++) {
      int comp=n-a[i];
      if (compliments.contains(comp)) return true;
      compliments.add(comp);
    }
    return false;
  }

}
