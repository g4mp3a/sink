package futures;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class HowManyVegetables implements Callable<Integer> {
  @Override
  public Integer call() throws Exception {
    System.out.println("Olivia is counting vegetables...");
    Thread.sleep(3000);
    return 42;
  }
}

public class Future101 {
  public static void main(final String args[]) throws ExecutionException, InterruptedException {
    System.out.println("Barron asks Olivia how many vegetables are in the pantry.");
    final ExecutorService executor=Executors.newSingleThreadExecutor();
    final Future<Integer> result=executor.submit(new HowManyVegetables());
    System.out.println("Barron can do other things while he waits for the result...");
    System.out.println("Olivia responded with "+result.get());
    executor.shutdown();
  }
}
