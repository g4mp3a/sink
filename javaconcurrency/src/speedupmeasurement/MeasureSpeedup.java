package speedupmeasurement;

/**
 * Measure the speedup of a parallel algorithm
 */
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

// parallel implementation
class RecursiveSum extends RecursiveTask<Long> {

  private final long lo, hi;

  // constructor for recursive instantiations
  public RecursiveSum(final long lo, final long hi) {
    this.lo=lo;
    this.hi=hi;
  }

  // returns sum of numbers between lo and hi
  @Override
  protected Long compute() {
    if ((this.hi-this.lo)<100000) { // base case threshold; a value of 100 decresed efficiency
      long total=0;
      for (long i=this.lo; i<=this.hi; i++) {
        total+=i;
      }
      return total;
    }
    final long mid=(this.hi+this.lo)/2; // middle index for split
    final RecursiveSum left=new RecursiveSum(this.lo,mid);
    final RecursiveSum right=new RecursiveSum(mid+1,this.hi);
    left.fork(); // forked thread computes left half
    return right.compute()+left.join(); // current thread computes right half
  }
}

public class MeasureSpeedup {

  // sequential implementation
  private static long sequentialSum(final long lo, final long hi) {
    long total=0;
    for (long i=lo; i<=hi; i++) {
      total+=i;
    }
    return total;
  }

  public static void main(final String args[]) {
    final int NUM_EVAL_RUNS=10;
    final long SUM_VALUE=1_000_000_000L;

    System.out.println("Evaluating Sequential Implementation...");
    final long sequentialResult=sequentialSum(0,SUM_VALUE); // "warm up"
    double sequentialTime=0;
    for (int i=0; i<NUM_EVAL_RUNS; i++) {
      final long start=System.currentTimeMillis();
      sequentialSum(0,SUM_VALUE);
      sequentialTime+=System.currentTimeMillis()-start;
    }
    sequentialTime/=NUM_EVAL_RUNS;

    System.out.println("Evaluating Parallel Implementation...");
    ForkJoinPool pool=ForkJoinPool.commonPool();
    final long parallelResult=pool.invoke(new RecursiveSum(0,SUM_VALUE)); // "warm up"
    pool.shutdown();
    double parallelTime=0;
    for (int i=0; i<NUM_EVAL_RUNS; i++) {
      final long start=System.currentTimeMillis();
      pool=ForkJoinPool.commonPool();
      pool.invoke(new RecursiveSum(0,SUM_VALUE));
      pool.shutdown();
      parallelTime+=System.currentTimeMillis()-start;
    }
    parallelTime/=NUM_EVAL_RUNS;

    // display sequential and parallel results for comparison
    if (sequentialResult!=parallelResult)
      throw new Error("ERROR: sequentialResult and parallelResult do not match!");

    System.out.format("Average Sequential Time: %.1f ms\n",sequentialTime);
    System.out.format("Average Parallel Time: %.1f ms\n",parallelTime);
    System.out.format("Speedup: %.2f \n",sequentialTime/parallelTime);
    System.out.format("Efficiency: %.2f%%\n",
        (100*(sequentialTime/parallelTime))/Runtime.getRuntime().availableProcessors());
  }
}
