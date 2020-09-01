package gp.lis;

import java.util.ArrayList;
import java.util.List;

public class LIS {
  private int[] a;

  public LIS() {
  }

  public LIS(final int[] a) {
    this.a=a;
  }

  @SuppressWarnings("boxing")
  public int getLengthOfLIS() {
    if ((this.a==null)||(this.a.length==0))
      return 0;

    final List<Integer> tail=new ArrayList<>();
    tail.add(this.a[0]);

    for (int i=1; i<this.a.length; i++) {
      // If entry is smaller than the smallest end candidate, then
      // Set it as the new smallest end candidate at tail[0]
      if (this.a[i]<tail.get(0)) {
        tail.set(0,this.a[i]);
      }
      // If entry is larger than the largest end candidate, then
      // Extend the largest list using it <=> add entry to tail
      else if (this.a[i]>tail.get(tail.size()-1)) {
        tail.add(this.a[i]);
      }
      // a[i] is going to replace ceilValue in tail and become the new end
      // candidate of an existing list
      else {
        tail.set(this.ceilValue(tail,this.a[i]),this.a[i]);
      }
    }
    return tail.size();

  }

  public LIS setA(final int[] a) {
    this.a=a;
    return this;
  }

  @SuppressWarnings("boxing")
  private int ceilValue(final List<Integer> list, final int key) {

    int l=0, r=list.size()-1;
    while ((r-l)>1) {

      final int m=l+((r-l)/2);
      if (list.get(m)>=key) {
        r=m;
      } else {
        l=m;
      }
    }
    return r;
  }
}
