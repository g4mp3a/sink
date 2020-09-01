package gp.random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CountingSort {

  // Complete the countSort function below.
  static void countSort(List<List<String>> arr) {
    int n=arr.size();
    int counter=1;
    ArrayList<String>[] countingArr=(ArrayList<String>[]) new ArrayList[100];
    for (List<String> entry : arr) {
      int i=Integer.parseInt(entry.get(0));
      String s=entry.get(1);
      if (counter<=n/2) s="-";
      counter++;
      if (countingArr[i]==null) countingArr[i]=new ArrayList<String>();
      countingArr[i].add(s);
    }

    // List<String> result = new ArrayList<String>();
    for (int i=0; i<100; ++i) {
      if (countingArr[i]==null) continue;
      // result.addAll(countingArr[i]);
      System.out.print(countingArr[i].stream().collect(Collectors.joining(" ")));
      if (i<99) System.out.print(" ");
    }
    // System.out.println(result.stream().collect(Collectors.joining(" ")));

    // SortedMap<Integer, List<String>> countingMap = new TreeMap<>();
    // for (List<String> entry : arr) {
    // int i = Integer.parseInt(entry.get(0));
    // String s = entry.get(1);
    // if (counter <= n/2) s = "-";
    // counter++;
    // if (!countingMap.containsKey(i))
    // countingMap.put(i, new ArrayList<String>());
    // countingMap.get(i).add(s);

    // //List<String> l = countingMap.putIfAbsent(i, new ArrayList<String>());
    // //if (l != null) l.add(s);
    // //else countingMap.get(i).add(s);
    // }
    // System.out.println(
    // countingMap.values().stream()
    // .flatMap(Collection::stream)
    // .collect(Collectors.joining(" ")));
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));

    int n=Integer.parseInt(bufferedReader.readLine().trim());

    List<List<String>> arr=new ArrayList<>();

    IntStream.range(0,n).forEach(i -> {
      try {
        arr.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$","").split(" "))
            .collect(toList()));
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    countSort(arr);

    bufferedReader.close();
  }

  /*
   * public static void main(String[] args) throws IOException { BufferedReader
   * bufferedReader = new BufferedReader(new InputStreamReader(System.in)); int n
   * = Integer.parseInt(bufferedReader.readLine().trim()); ArrayList<String>[]
   * countingArr = (ArrayList<String>[])new ArrayList[100]; for (int j=1; j<=n;
   * ++j) { String[] entry = bufferedReader.readLine().replaceAll("\\s+$",
   * "").split(" "); int i = Integer.parseInt(entry[0]); String s = entry[1]; if
   * (j <= n/2) s = "-"; if(countingArr[i] == null) countingArr[i] = new
   * ArrayList<String>(); countingArr[i].add(s); }
   *
   * // List<String> result = new ArrayList<String>(); for (int i=0; i<100; ++i) {
   * if (countingArr[i] == null) continue; // result.addAll(countingArr[i]);
   * System.out.print(countingArr[i].stream() .collect(Collectors.joining(" ")));
   * if(i < 99) System.out.print(" "); } bufferedReader.close(); }
   */
}
