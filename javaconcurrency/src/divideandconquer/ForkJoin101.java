package divideandconquer;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class RecursiveSum extends RecursiveTask<Long> {

  private final long lo, hi;

  public RecursiveSum(final long lo, final long hi) {
    this.lo=lo;
    this.hi=hi;
  }

  @Override
  protected Long compute() {
    if ((hi-lo)<=100_000) { // base case threshold
      long total=0;
      for (long i=lo; i<=hi; i++) {
        total+=i;
      }
      return total;
    }
    final long mid=(hi+lo)/2; // middle index for split
    final RecursiveSum left=new RecursiveSum(lo,mid);
    final RecursiveSum right=new RecursiveSum(mid+1,hi);
    left.fork(); // forked thread computes left half asynchronously
    return right.compute()+left.join(); // current thread computes right half
  }
}

public class ForkJoin101 {
  public static void main(final String args[]) {
    final ForkJoinPool pool=ForkJoinPool.commonPool();
    final Long total=pool.invoke(new RecursiveSum(0,1_000_000_000));
    pool.shutdown();
    System.out.println("Total sum is "+total);
  }
}
