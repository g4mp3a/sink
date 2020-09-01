package gp.sums;

public class NumbersInAnArrayWithAGivenSum {
  private int[] a;

  public NumbersInAnArrayWithAGivenSum setA(int[] a) {
    this.a=a;
    return this;
  }

  public NumbersInAnArrayWithAGivenSum() {
  }

  public NumbersInAnArrayWithAGivenSum(int[] a) {
    this.a=a;
  }

  public boolean findTwoNumbersInASortedArrayWithGivenSum(int sum) {
    return findTwoNumbersInASortedArrayWithGivenSum(sum,-1);
  }

  public boolean findTwoNumbersInASortedArrayWithGivenSum(int sum, int excludeIndex) {
    if (a==null||a.length==0)
      return false;

    int l=0, r=a.length-1;
    while (r>l) {
      if (r==excludeIndex)
        r--;
      if (l==excludeIndex)
        l++;

      if (a[r]+a[l]==sum)
        return true;
      else if (a[r]+a[l]>sum)
        r--;
      else
        l++;
    }
    return false;
  }

  public boolean findThreeNumbersInASortedArrayWithGivenSum(int sum) {
    for (int i=0; i<a.length; i++) {
      if (findTwoNumbersInASortedArrayWithGivenSum(sum-a[i],i))
        return true;
    }
    return false;
  }
}
