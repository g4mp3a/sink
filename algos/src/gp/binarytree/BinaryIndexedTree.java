package gp.binarytree;

/**
 * Ideas: https://www.youtube.com/watch?v=v_wj_mOAlig&t=1s
 *
 */
public class BinaryIndexedTree {

  private int[] bit;

  public void buildBITNLogN(int[] a) {
    bit=new int[a.length+1];
    for (int i=0; i<a.length; i++)
      add(i,a[i]);
  }

  public void buildBITN(int[] a) {
    bit=new int[a.length+1];
    System.arraycopy(a,0,bit,1,a.length);

    for (int i=1; i<bit.length; i++) {
      int j=i+(i&(-i));
      if (j<bit.length) bit[j]+=bit[i];
    }
  }

  public void add(int idx, int n) {
    idx++;
    while (idx<bit.length) {
      bit[idx]+=n;
      idx+=idx&(-idx);
    }
  }

  public long prefixSum(int idx) {
    idx++;
    int result=0;
    while (idx>0) {
      result+=bit[idx];
      idx-=idx&(-idx);
    }
    return result;
  }

  public long rangeSum(int fromIdx, int toIdx) {
    return prefixSum(toIdx)-prefixSum(fromIdx-1);
  }
}
