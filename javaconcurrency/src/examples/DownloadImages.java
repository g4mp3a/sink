package examples;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

class ImageDownloader {

  private int[] images;

  public ImageDownloader(int[] n) {

    this.images=n;
  }

  public int downloadAll() {

    int totalBytes=0;
    for (int num : images) totalBytes+=downloadImage(num);
    return totalBytes;
  }

  private int downloadImage(int imageNumber) {

    try {
      imageNumber=(Math.abs(imageNumber)%50)+1; // force number between 1 and 50
      URL photoURL=new URL(
          String.format("http://699340.youcanlearnit.net/image%03d.jpg",imageNumber));
      BufferedInputStream in=new BufferedInputStream(photoURL.openStream());
      int bytesRead, totalBytes=0;
      byte buffer[]=new byte[1024];
      while ( (bytesRead=in.read(buffer,0,1024))!=-1 ) totalBytes+=bytesRead;
      return totalBytes;
    } catch ( IOException e ) {
      e.printStackTrace();
    }
    return 0;
  }
}

class ParallelImageDownloader {

  private int[] images;

  public ParallelImageDownloader(int[] n) {

    this.images=n;
  }

  public int downloadAll() {

    int numWorkers=Runtime.getRuntime().availableProcessors();
    ExecutorService pool=Executors.newFixedThreadPool(numWorkers);

    List<Future<Integer>> futures=new ArrayList<>();
    for (int num : images) {
      Callable<Integer> imageRequest=() -> { return downloadImage(num); };
      futures.add(pool.submit(imageRequest));
    }

    int totalBytes=0;
    try {
      for (Future<Integer> f : futures) totalBytes+=f.get();
    } catch ( InterruptedException|ExecutionException e ) {
      e.printStackTrace();
    }

    pool.shutdown();
    return totalBytes;
  }

  private int downloadImage(int imageNumber) {

    try {
      imageNumber=(Math.abs(imageNumber)%50)+1; // force number between 1 and 50
      URL photoURL=new URL(
          String.format("http://699340.youcanlearnit.net/image%03d.jpg",imageNumber));
      BufferedInputStream in=new BufferedInputStream(photoURL.openStream());
      int bytesRead, totalBytes=0;
      byte buffer[]=new byte[1024];
      while ( (bytesRead=in.read(buffer,0,1024))!=-1 ) totalBytes+=bytesRead;
      return totalBytes;
    } catch ( IOException e ) {
      e.printStackTrace();
    }
    return 0;
  }
}

public class DownloadImages {

  /* evaluate performance of sequential and parallel implementations */
  public static void main(String[] args) {

    final int NUM_EVAL_RUNS=3;
    final int[] IMAGE_NUMS=IntStream.rangeClosed(1,200).toArray(); // images to download

    System.out.println("Evaluating Sequential Implementation...");
    ImageDownloader sid=new ImageDownloader(IMAGE_NUMS);
    int sequentialResult=sid.downloadAll();
    double sequentialTime=0;
    for (int i=0; i<NUM_EVAL_RUNS; i++) {
      long start=System.currentTimeMillis();
      sid.downloadAll();
      sequentialTime+=System.currentTimeMillis()-start;
    }
    sequentialTime/=NUM_EVAL_RUNS;

    System.out.println("Evaluating Parallel Implementation...");
    ParallelImageDownloader pid=new ParallelImageDownloader(IMAGE_NUMS);
    int parallelResult=pid.downloadAll();
    double parallelTime=0;
    for (int i=0; i<NUM_EVAL_RUNS; i++) {
      long start=System.currentTimeMillis();
      pid.downloadAll();
      parallelTime+=System.currentTimeMillis()-start;
    }
    parallelTime/=NUM_EVAL_RUNS;

    // display sequential and parallel results for comparison
    if ( sequentialResult!=parallelResult )
      throw new Error("ERROR: sequentialResult and parallelResult do not match!");
    System.out.format("Downloaded %d images totaling %.1f MB\n",IMAGE_NUMS.length,
        sequentialResult/1e6);
    System.out.format("Average Sequential Time: %.1f ms\n",sequentialTime);
    System.out.format("Average Parallel Time: %.1f ms\n",parallelTime);
    System.out.format("Speedup: %.2f \n",sequentialTime/parallelTime);
    System.out.format("Efficiency: %.2f%%\n",
        100*(sequentialTime/parallelTime)/Runtime.getRuntime().availableProcessors());
  }
}
