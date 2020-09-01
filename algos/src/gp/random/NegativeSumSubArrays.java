import java.io.*;
import java.util.*;
import java.util.stream.*;

public class NegativeSumSubArrays {

    public static void main(String[] args) {
      
      try (Scanner scanner = new Scanner(System.in)) {
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i=0; i<n; i++) a[i]=in.nextInt();
        
        /*
        int[] a = Arrays.stream("1 -2 4 -5 1".split(" "))
                        .map(s->s.trim())
                        .filter(s->!s.isEmpty())
                        .mapToInt(Integer::parseInt)
                        .toArray();
        */
          
        int count=0;
        for (int j=1; j<=a.length; j++) {
          for (int i=0; i<=a.length-j; i++) {
            int sum = Arrays.stream(a,i,i+j).sum();
            if (sum<0) { count++; System.out.print(sum + ", "); }
          }
        }
        System.out.println("\n"+count);
      }
    }
}