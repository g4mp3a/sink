package examples;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class MergeSorter {

  private int[] array;

  public MergeSorter(int[] a) {

    this.array=a;
  }

  public int[] sort() {

    sort(0,array.length-1);
    return array;
  }

  private void sort(int l, int r) {

    if ( l<r ) {
      int m=(l+r)/2;
      sort(l,m);
      sort(m+1,r);
      merge(l,m,r);
    }
  }

  private void merge(int l, int m, int r) {

    int larr[]=Arrays.copyOfRange(array,l,m+1);
    int rarr[]=Arrays.copyOfRange(array,m+1,r+1);

    int lIdx=0, rIdx=0, mergeIdx=l;

    while ( lIdx<m-l+1||rIdx<r-m ) {
      if ( lIdx<m-l+1&&rIdx<r-m ) {
        if ( larr[lIdx]<=rarr[rIdx] ) {
          array[mergeIdx]=larr[lIdx];
          lIdx++;
        } else {
          array[mergeIdx]=rarr[rIdx];
          rIdx++;
        }
      } else if ( lIdx<m-l+1 ) {
        array[mergeIdx]=larr[lIdx];
        lIdx++;
      } else if ( rIdx<r-m ) {
        array[mergeIdx]=rarr[rIdx];
        rIdx++;
      }
      mergeIdx++;
    }
  }

}

class ParallelMergeSorter {

  int[] array;

  public ParallelMergeSorter(int[] a) {

    this.array=a;
  }

  public int[] sort() {

    int numWorkers=Runtime.getRuntime().availableProcessors();
    ForkJoinPool pool=new ForkJoinPool(numWorkers);
    pool.invoke(new ParallelWorker(0,array.length-1));
    return array;
  }

  private class ParallelWorker extends RecursiveAction {

    private int l, r;

    public ParallelWorker(int l, int r) {

      this.l=l;
      this.r=r;
    }

    @Override
    protected void compute() {

      if ( l<r ) {
        int m=(r+l)/2;
        ParallelWorker leftWorker=new ParallelWorker(l,m);
        ParallelWorker rightWorker=new ParallelWorker(m+1,r);
        invokeAll(leftWorker,rightWorker);
        merge(l,m,r);
      }
    }

  }

  private void merge(int l, int m, int r) {

    int larr[]=Arrays.copyOfRange(array,l,m+1);
    int rarr[]=Arrays.copyOfRange(array,m+1,r+1);

    int lIdx=0, rIdx=0, mergeIdx=l;

    while ( lIdx<m-l+1||rIdx<r-m ) {
      if ( lIdx<m-l+1&&rIdx<r-m ) {
        if ( larr[lIdx]<=rarr[rIdx] ) {
          array[mergeIdx]=larr[lIdx];
          lIdx++;
        } else {
          array[mergeIdx]=rarr[rIdx];
          rIdx++;
        }
      } else if ( lIdx<m-l+1 ) {
        array[mergeIdx]=larr[lIdx];
        lIdx++;
      } else if ( rIdx<r-m ) {
        array[mergeIdx]=rarr[rIdx];
        rIdx++;
      }
      mergeIdx++;
    }
  }
}

public class MergeSort {

  /* helper function to generate array of random integers */
  public static int[] generateRandomArray(int length) {

    System.out.format("Generating random array int[%d]...\n",length);
    Random rand=new Random();
    int[] output=new int[length];
    for (int i=0; i<length; i++) output[i]=rand.nextInt();
    return output;
  }

  /* evaluate performance of sequential and parallel implementations */
  public static void main(String[] args) {

    final int NUM_EVAL_RUNS=5;
    final int[] input=generateRandomArray(100_000_000);

    System.out.println("Evaluating Sequential Implementation...");
    MergeSorter sms=new MergeSorter(Arrays.copyOf(input,input.length));
    int[] sequentialResult=sms.sort();
    double sequentialTime=0;
    for (int i=0; i<NUM_EVAL_RUNS; i++) {
      sms=new MergeSorter(Arrays.copyOf(input,input.length));
      long start=System.currentTimeMillis();
      sms.sort();
      sequentialTime+=System.currentTimeMillis()-start;
    }
    sequentialTime/=NUM_EVAL_RUNS;

    System.out.println("Evaluating Parallel Implementation...");
    ParallelMergeSorter pms=new ParallelMergeSorter(Arrays.copyOf(input,input.length));
    int[] parallelResult=pms.sort();
    double parallelTime=0;
    for (int i=0; i<NUM_EVAL_RUNS; i++) {
      pms=new ParallelMergeSorter(Arrays.copyOf(input,input.length));
      long start=System.currentTimeMillis();
      pms.sort();
      parallelTime+=System.currentTimeMillis()-start;
    }
    parallelTime/=NUM_EVAL_RUNS;

    // display sequential and parallel results for comparison
    if ( !Arrays.equals(sequentialResult,parallelResult) )
      throw new Error("ERROR: sequentialResult and parallelResult do not match!");
    System.out.format("Average Sequential Time: %.1f ms\n",sequentialTime);
    System.out.format("Average Parallel Time: %.1f ms\n",parallelTime);
    System.out.format("Speedup: %.2f \n",sequentialTime/parallelTime);
    System.out.format("Efficiency: %.2f%%\n",
        100*(sequentialTime/parallelTime)/Runtime.getRuntime().availableProcessors());
  }

}
