package gp.random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MatrixRotation {

  // Complete the matrixRotation function below.
  static void matrixRotation(List<List<Integer>> matrix, int r) {

    int M=matrix.size(), N=matrix.get(0).size();
    List<Integer> temp=new ArrayList<>(M*N);
    /**
     * k - starting row index m - ending row index l - starting column index n -
     * ending column index
     **/
    int m=M, n=N, k=0, l=0;
    while ( k<m&&l<n ) {
      List<Integer> currentRing=new ArrayList<>();

      // Copy first row from remaining rows
      for (int i=l; i<n; ++i) currentRing.add(matrix.get(k).get(i));
      k++;

      // copy last column from remaining columns
      for (int i=k; i<m; ++i) currentRing.add(matrix.get(i).get(n-1));
      n--;

      // copy last row from remaining rows
      if ( k<m ) {
        for (int i=n-1; i>=l; --i) currentRing.add(matrix.get(m-1).get(i));
        m--;
      }

      // copy first column from remaining columns
      if ( l<n ) {
        for (int i=m-1; i>=k; --i) currentRing.add(matrix.get(i).get(l));
        l++;
      }

      // rotate elements of current ring if
      // number of elements in current ring is greater than r
      // else break
      if ( currentRing.size()>r ) {
        // rotateList(currentRing);
        Collections.rotate(currentRing,-1*r);
        temp.addAll(currentRing);
      } else break;
    }
    fillMatrixSpiral(matrix,temp);

    // Print matrix
    for (int row=0; row<M; ++row) {
      for (int col=0; col<N; ++col) System.out.print(matrix.get(row).get(col)+" ");
      System.out.println();
    }
  }

  static void fillMatrixSpiral(List<List<Integer>> matrix, List<Integer> rotatedRings) {

    int M=matrix.size(), N=matrix.get(0).size();
    Iterator<Integer> itr=rotatedRings.iterator();

    /**
     * k - starting row index m - ending row index l - starting column index n -
     * ending column index
     **/
    int m=M, n=N, k=0, l=0;
    while ( k<m&&l<n&&itr.hasNext() ) {

      // Process first row from remaining rows
      for (int i=l; i<n; ++i) matrix.get(k).set(i,itr.next());
      k++;

      // Process last column from remaining columns
      for (int i=k; i<m; ++i) if ( itr.hasNext() ) matrix.get(i).set((n-1),itr.next());
      n--;

      // Process last row from remaining rows
      if ( k<m ) {
        for (int i=n-1; i>=l; --i) if ( itr.hasNext() ) matrix.get(m-1).set(i,itr.next());
        m--;
      }

      // Process first column from remaining columns
      if ( l<n ) {
        for (int i=m-1; i>=k; --i) if ( itr.hasNext() ) matrix.get(i).set(l,itr.next());
        l++;
      }
    }
  }

  static void rotateList(List<Integer> l, int r) {
    // reverse(0, r, l);

    // reverse(r, l.size(), l);
    // reverse(l);

    /*
     * int [] result = new int[l.size()]; for(int i = 0; i < l.size(); i++){
     * result[(i+(l.size()-r)) % l.size()] = l.get(i); }
     */

    Collections.rotate(l,-1*r);
  }

  public static void main(String[] args) throws IOException {

    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));

    String[] mnr=bufferedReader.readLine().replaceAll("\\s+$","").split(" ");

    int m=Integer.parseInt(mnr[0]);

    int n=Integer.parseInt(mnr[1]);

    int r=Integer.parseInt(mnr[2]);

    List<List<Integer>> matrix=new ArrayList<>();

    IntStream.range(0,m).forEach(i -> {
      try {
        matrix.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$","").split(" "))
            .map(Integer::parseInt).collect(toList()));
      } catch ( IOException ex ) {
        throw new RuntimeException(ex);
      }
    });

    matrixRotation(matrix,r);

    bufferedReader.close();
  }
}
