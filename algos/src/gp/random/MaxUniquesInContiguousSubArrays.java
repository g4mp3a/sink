package gp.random;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MaxUniquesInContiguousSubArrays {

  public static void main(String[] args) {

    Scanner in=new Scanner(System.in);
    Deque<Integer> deque=new ArrayDeque<>();
    int n=in.nextInt();
    int m=in.nextInt();

    if ( m==1 ) {
      System.out.println(1);
      return;
    }

    int result=0;
    Set<Integer> s=new HashSet<>();
    for (int i=0; i<n; i++) {
      int num=in.nextInt();

      deque.add(num);
      s.add(num);

      if ( deque.size()==m ) {
        if ( s.size()==m ) {
          System.out.println(m);
          return;
        }
        if ( s.size()>result ) result=s.size();
        int f=deque.removeFirst();
        if ( !deque.contains(f) ) s.remove(f);
      }
    }
    System.out.println(result);
    in.close();
  }
}
