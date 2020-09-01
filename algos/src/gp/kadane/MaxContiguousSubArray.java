package gp.kadane;

import java.util.Arrays;

/**
 * Find sum of max sub array
 * 
 * @author gautampriya
 *
 */

public class MaxContiguousSubArray {

  public int sumOfMaxSubArray(final int[] a) {
    int maxEndingHere=0;
    int maxSoFar=0;

    for (final int i : a) {
      maxEndingHere=Math.max(0,maxEndingHere+i);
      maxSoFar=Math.max(maxSoFar,maxEndingHere);
    }
    return maxSoFar;
  }

  public int[] getMaxContiguousSubArray(final int[] a) {

    final int[] EMPTY= {};
    int maxEndingHere=0;
    int maxSoFar=0;
    int startIdx=0;
    int maxArrayStartIdx=0;
    int maxArrayEndIdx=0;

    for (int i=0; i<a.length; i++) {

      if (0>(maxEndingHere+a[i])) {
        maxEndingHere=0;
        startIdx=i+1;
      } else {
        maxEndingHere+=a[i];
      }

      if (maxSoFar<maxEndingHere) {
        maxSoFar=maxEndingHere;
        maxArrayStartIdx=startIdx;
        maxArrayEndIdx=i;
      }
    }

    if (maxArrayStartIdx<=maxArrayEndIdx)
      return Arrays.copyOfRange(a,maxArrayStartIdx,maxArrayEndIdx+1);

    return EMPTY;

  }

}
