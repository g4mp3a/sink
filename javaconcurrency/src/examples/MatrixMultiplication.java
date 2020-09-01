package examples;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MatrixMultiplier {

  int[][] matA, matB;
  int numRowsA, numColsA, numRowsB, numColsB;

  MatrixMultiplier(int[][] inputA, int[][] inputB) {

    this.matA=inputA;
    this.matB=inputB;
    this.numRowsA=inputA.length;
    this.numColsA=inputA[0].length;
    this.numRowsB=inputB.length;
    this.numColsB=inputB[0].length;

    if ( numColsA!=numRowsB )
      throw new Error("Cant multiply!\nNum of columns in A should be equal to num of rows in B.");
  }

  public int[][] computeProduct() {

    int[][] result=new int[numRowsA][numColsB];
    for (int i=0; i<numRowsA; i++) {
      for (int j=0; j<numColsB; j++) {
        int sum=0;
        for (int k=0; k<numColsA; k++) sum+=matA[i][k]*matB[k][j];
        result[i][j]=sum;
      }
    }
    return result;
  }
}

class ParallelMatrixMultiplier {

  int[][] matA, matB;
  int numRowsA, numColsA, numRowsB, numColsB;

  ParallelMatrixMultiplier(int[][] inputA, int[][] inputB) {

    this.matA=inputA;
    this.matB=inputB;
    this.numRowsA=inputA.length;
    this.numColsA=inputA[0].length;
    this.numRowsB=inputB.length;
    this.numColsB=inputB[0].length;

    if ( numColsA!=numRowsB )
      throw new Error("Cant multiply!\nNum of columns in A should be equal to num of rows in B.");
  }

  public int[][] computeProduct() {

    int numWorkers=Runtime.getRuntime().availableProcessors();
    ExecutorService pool=Executors.newFixedThreadPool(numWorkers);

    // Calculate partial results
    int partitionSize=(int) Math.ceil((double) numRowsA/numWorkers);
    Future<int[][]>[] partialResults=new Future[numWorkers];
    for (int w=0; w<numWorkers; w++) {
      int startRowNum=Math.min(w*partitionSize,numRowsA);
      int endRowNum=Math.min(( w+1 )*partitionSize,numRowsA);
      partialResults[w]=pool.submit(new ParallelWorker(startRowNum,endRowNum));
    }

    // Merge partial results
    int[][] result=new int[numRowsA][numColsB];
    for (int w=0; w<numWorkers; w++) {
      try {
        int[][] partialResult=partialResults[w].get();
        for (int i=0; i<partialResult.length; i++)
          for (int j=0; j<numColsB; j++) result[i+( w*partitionSize )][j]=partialResult[i][j];
      } catch ( InterruptedException|ExecutionException exp ) {
        exp.printStackTrace();
      }
    }
    pool.shutdown();
    return result;
  }

  private class ParallelWorker implements Callable<int[][]> {

    int startRow, endRow;

    public ParallelWorker(int start, int end) {

      this.startRow=start;
      this.endRow=end;
    }

    @Override
    public int[][] call() throws Exception {

      int[][] partialResult=new int[endRow-startRow][numColsB];
      for (int i=0; i<endRow-startRow; i++) {
        for (int j=0; j<numColsB; j++) {
          int sum=0;
          for (int k=0; k<numColsA; k++) sum+=matA[i+startRow][k]*matB[k][j];
          partialResult[i][j]=sum;
        }
      }
      return partialResult;
    }
  }
}

public class MatrixMultiplication {

  // generates a random matrix of size m*n
  public static int[][] generateRandomMatrix(int m, int n) {

    Random randNumGenerator=new Random();
    int[][] r=new int[m][n];
    for (int i=0; i<m; i++) for (int j=0; j<n; j++) r[i][j]=randNumGenerator.nextInt(100);
    return r;
  }

  // evaluate performance of sequential and parallel implementations
  public static void main(String args[]) {

    final int NUM_EVAL_RUNS=5;
    final int[][] A=generateRandomMatrix(2000,2000);
    final int[][] B=generateRandomMatrix(2000,2000);

    MatrixMultiplier mm=new MatrixMultiplier(A,B);
    int[][] sequentialResult=mm.computeProduct();
    double sequentialTime=0;
    for (int i=0; i<NUM_EVAL_RUNS; i++) {
      long start=System.currentTimeMillis();
      mm.computeProduct();
      sequentialTime+=System.currentTimeMillis()-start;
    }
    sequentialTime/=NUM_EVAL_RUNS;

    ParallelMatrixMultiplier pmm=new ParallelMatrixMultiplier(A,B);
    int[][] parallelResult=pmm.computeProduct();
    double parallelTime=0;
    for (int i=0; i<NUM_EVAL_RUNS; i++) {
      long start=System.currentTimeMillis();
      pmm.computeProduct();
      parallelTime+=System.currentTimeMillis()-start;
    }
    parallelTime/=NUM_EVAL_RUNS;

    // display sequential and parallel results for comparison
    if ( !Arrays.deepEquals(sequentialResult,parallelResult) )
      throw new Error("ERROR: sequentialResult and parallelResult do not match!");

    System.out.format("Average Sequential Time: %.1f ms\n",sequentialTime);
    System.out.format("Average Parallel Time: %.1f ms\n",parallelTime);
    System.out.format("Speedup: %.2f \n",sequentialTime/parallelTime);
    System.out.format("Efficiency: %.2f%%\n",
        100*( sequentialTime/parallelTime )/Runtime.getRuntime().availableProcessors());
  }
}
