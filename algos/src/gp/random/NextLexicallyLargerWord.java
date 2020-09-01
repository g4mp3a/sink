package gp.random;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class NextLexicallyLargerWord {

  // Complete the biggerIsGreater function below.
  static String biggerIsGreater(String w) {

    if ( w==null||w.trim().isEmpty()||w.trim().length()==0||w.trim().length()==1 )
      return "no answer";

    Character[] t=w.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
    Arrays.sort(t,Collections.reverseOrder());
    if ( Arrays.toString(t).equals(w) ) return "no answer";

    char[] array=w.toCharArray();
    // Find longest non-increasing suffix
    int i=array.length-1;
    while ( i>0&&array[i-1]>=array[i] ) i--;
    // Now i is the head index of the suffix

    // Are we at the last permutation already?
    if ( i<=0 ) return "no answer";

    // Let array[i - 1] be the pivot
    // Find rightmost element that exceeds the pivot
    int j=array.length-1;
    while ( array[j]<=array[i-1] ) j--;
    // Now the value array[j] will become the new pivot
    // Assertion: j >= i

    // Swap the pivot with j
    char temp=array[i-1];
    array[i-1]=array[j];
    array[j]=temp;

    // Reverse the suffix
    j=array.length-1;
    while ( i<j ) {
      temp=array[i];
      array[i]=array[j];
      array[j]=temp;
      i++;
      j--;
    }
    return new String(array);
  }

  private static final Scanner scanner=new Scanner(System.in);

  public static void main(String[] args) throws IOException {

    BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int T=scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int TItr=0; TItr<T; TItr++) {
      String w=scanner.nextLine();

      String result=biggerIsGreater(w);

      bufferedWriter.write(result);
      bufferedWriter.newLine();
    }

    bufferedWriter.close();

    scanner.close();
  }
}
