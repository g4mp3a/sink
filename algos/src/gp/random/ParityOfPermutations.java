package gp.random;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

/**
 * The formula in the link
 * https://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
 * is:
 * 
 * ( (grid width odd) && (#inversions even) ) || ( (grid width even) && ((blank
 * on odd row from bottom) == (#inversions even)) )
 * 
 * Note how the width is a factor but not the height.
 * 
 * The 3 consecutive indices rotation can be converted to a rectangle with width
 * 3 and any height h.Moving a tile up or down means rotating three consecutive
 * indices in either direction.
 * 
 * The given array can be then compared to a rectangle of width 3, and any
 * height h, so that total number of tiles is (3*h)-1.(Like it was (n*n)-1 in
 * the original 15 tile puzzle)
 * 
 * The question remains now is how to fit any number into 3*h-1, eg:n=15. The
 * next number after 15 that satisfies the form 3*h-1 is 17. It does not make a
 * difference if the permutation of a sequence of natural numbers incrementing
 * from 1 to 15 is extended to 17.Why? Because now the extended problem has 16
 * and 17 in the right places, and cannot be involved in rotation anyways.It is
 * as if the original array was a permutation of first 17 natural numbers, where
 * 16 and 17 were in the right place. Same goes for n=16. Add 17 at the end,and
 * assume as if the original array was a permutation of first 17 natural
 * numbers, where 17 was in the right place.
 * 
 * So now, the first part of the formula: (grid width odd) && (#inversions even)
 * 
 * is applicable, width being 3.
 * 
 * So simply count the number of inversions, and return Yes if that is even,
 * else no.
 */

public class ParityOfPermutations {

  // Complete the larrysArray function below.
  static String larrysArray(int[] A) {
    int numInversions=0;
    for (int i=0; i<A.length; ++i)
      for (int j=i+1; j<A.length; ++j)
        if (A[j]<A[i])
          ++numInversions;
    // since, width(= 3) is odd, it is solvable only when
    // numInversions is even
    return (numInversions%2==0)? "YES":"NO";
  }

  private static final Scanner scanner=new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int t=scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int tItr=0; tItr<t; tItr++) {
      int n=scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int[] A=new int[n];

      String[] AItems=scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i=0; i<n; i++) {
        int AItem=Integer.parseInt(AItems[i]);
        A[i]=AItem;
      }

      String result=larrysArray(A);

      bufferedWriter.write(result);
      bufferedWriter.newLine();
    }

    bufferedWriter.close();

    scanner.close();
  }
}
