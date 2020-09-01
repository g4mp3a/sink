package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class VegetableChopper extends Thread {
  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName()+" chopped a vegetable!");
  }
}

public class ThreadPool101 {
  public static void main(final String args[]) {
    final int numProcs=Runtime.getRuntime().availableProcessors();
    final ExecutorService pool=Executors.newFixedThreadPool(numProcs);
    for (int i=0; i<100; i++) {
      pool.submit(new VegetableChopper());
    }
    pool.shutdown();
  }
}
