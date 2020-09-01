package gp.random;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class SmallestLexLargerWord {

  // Complete the biggerIsGreater function below.
  static String biggerIsGreater(String w) {

    if ( w==null||w.trim().isEmpty()||w.trim().length()==0||w.trim().length()==1 )
      return "no answer";

    Character[] t=w.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
    Arrays.sort(t,Collections.reverseOrder());
    if ( Arrays.toString(t).equals(w) ) return "no answer";

    char[] c=w.toCharArray();
    int sIdx=-1, eIdx=c.length-1;
    char tmp=Character.MIN_VALUE;
    // Problem is in the loop!
    for (int i=eIdx; i>-1; i--) {
      for (int j=i-1; j>-1; j--) {
        if ( c[i]>c[j] ) {
          tmp=c[j];
          c[j]=c[i];
          c[i]=tmp;
          sIdx=j;
          break;
        }
      }
      if ( sIdx!=-1 ) break;
    }
    if ( sIdx==-1 ) return "no answer";
    Arrays.sort(c,sIdx+1,eIdx+1);
    return new String(c);
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
