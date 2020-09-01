package gp.random;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Result {

  /*
   * Complete the 'getTotalX' function below.
   *
   * The function is expected to return an INTEGER. The function accepts following
   * parameters: 1. INTEGER_ARRAY a 2. INTEGER_ARRAY b
   */

  public static int getTotalX(List<Integer> a, List<Integer> b) {

    Integer aMax=a.stream().mapToInt(v -> v).max().orElseThrow(NoSuchElementException::new);
    Integer bMin=b.stream().mapToInt(v -> v).min().orElseThrow(NoSuchElementException::new);

    a.removeAll(Arrays.asList(aMax));
    b.removeAll(Arrays.asList(bMin));
    List<Integer> aPruned=a.stream().filter(v -> aMax%v!=0).collect(Collectors.toList());
    List<Integer> bPruned=b.stream().filter(v -> v%bMin!=0).collect(Collectors.toList());

    if ( aMax>bMin||bMin%aMax!=0 ) return 0;
    int result=0;
    for (int i=aMax; i<=bMin; i+=aMax) {
      if ( bMin%i==0&&isMultipleOfOtherNumbersInListA(i,aPruned)
          &&isFactorOfOtherNumbersInListB(i,bPruned) ) {
        result++;
      }
    }
    return result;
  }

  public static boolean isMultipleOfOtherNumbersInListA(int i, List<Integer> l) {

    if ( l==null||l.isEmpty() ) return true;
    for (int v : l) {
      if ( i%v!=0 ) return false;
    }
    return true;
  }

  public static boolean isFactorOfOtherNumbersInListB(int i, List<Integer> l) {

    if ( l==null||l.isEmpty() ) return true;
    for (int v : l) {
      if ( v%i!=0 ) return false;
    }
    return true;
  }

}

public class FactorMultiple {

  public static void main(String[] args) throws IOException {

    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] firstMultipleInput=bufferedReader.readLine().replaceAll("\\s+$","").split(" ");

    int n=Integer.parseInt(firstMultipleInput[0]);

    int m=Integer.parseInt(firstMultipleInput[1]);

    List<Integer> arr=Stream.of(bufferedReader.readLine().replaceAll("\\s+$","").split(" "))
        .map(Integer::parseInt).collect(toList());

    List<Integer> brr=Stream.of(bufferedReader.readLine().replaceAll("\\s+$","").split(" "))
        .map(Integer::parseInt).collect(toList());

    int total=Result.getTotalX(arr,brr);

    bufferedWriter.write(String.valueOf(total));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
