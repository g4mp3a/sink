package gp.random;

import java.util.Arrays;

public class CountArrayInversions {

  public void add(int[] bit, int val, int i) {

    while ( i<bit.length ) {
      bit[i]+=val;
      i+=i&-i;
    }
  }

  public int prefixSum(int[] bit, int i) {

    int sum=0;
    while ( i>0 ) {
      sum+=bit[i];
      i-=i&-i;
    }
    return sum;
  }

  public int[] convertToRankArray(int[] a) {

    int[] copyOfA=Arrays.copyOf(a,a.length);
    Arrays.sort(copyOfA);
    for (int i=0; i<a.length; i++) {
      a[i]=binarySearch(copyOfA,a[i])+1;
    }
    return a;
  }

  private int binarySearch(int[] a, int key) {

    int l=0;
    int h=a.length-1;
    while ( l<=h ) {
      int mid=l+( h-l )/2;
      if ( a[mid]==key ) {
        return mid;
      } else if ( a[mid]>key ) {
        h=mid-1;
      } else if ( a[mid]<key ) {
        l=mid+1;
      }
    }
    return -1;
  }

  public int countInversions(int[] a) {

    convertToRankArray(a);
    int[] bit=new int[a.length+1];
    for (int i=0; i<=a.length; i++) { bit[i]=0; }
    int count=0;
    for (int i=a.length-1; i>=0; i--) {
      count+=prefixSum(bit,a[i]-1);
      add(bit,1,a[i]);
    }

    return count;
  }

  public static void main(String[] args) {

    CountArrayInversions bit=new CountArrayInversions();
    int[] A= { 3, -5, 4, 1, 7 };
    int inversions=bit.countInversions(A);
    System.out.println(inversions);
  }

}
