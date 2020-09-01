package racecondition;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShopperWithBarrier extends Thread {

  public static int bagsOfChips=1; // start with one on the list
  private static Lock pencil=new ReentrantLock();
  private static CyclicBarrier fistBump=new CyclicBarrier(10);

  public ShopperWithBarrier(final String name) {
    this.setName(name);
  }

  /**
   * We use a barrier to synchronize the order in which Olivia and Barron thread
   * perform their actions. Barrier helps do this irrespective of the order in which
   * these threads are scheduled thus preventing a race condition. Barrier separates
   * the execution of the addition and multiplication operations.
   */
  @Override
  public void run() {
    if (this.getName().contains("Olivia")) {
      pencil.lock();
      try {
        bagsOfChips+=3;
        System.out.println(this.getName()+" ADDED three bags of chips.");
      } finally {
        pencil.unlock();
      }
      try {
        // Olivia thread adds 3 first and then joins/blocks at the barrier
        // till this barrier has a total of 10 threads waiting on it.
        // Then it continues with other tasks.
        fistBump.await();
      } catch (InterruptedException|BrokenBarrierException e) {
        e.printStackTrace();
      }
    } else { // "Barron"
      try {
        // Barron go straight to the barrier and waits at the barrier first
        // till the number of threads waiting at the barrier equals 10
        // and then proceeds to double the # of bags of chips.
        fistBump.await();
      } catch (InterruptedException|BrokenBarrierException e) {
        e.printStackTrace();
      }
      pencil.lock();
      try {
        bagsOfChips*=2;
        System.out.println(this.getName()+" DOUBLED the bags of chips.");
      } finally {
        pencil.unlock();
      }
    }
  }
}

public class Barrier101 {
  public static void main(final String args[]) throws InterruptedException {
    // create 10 shoppers
    final ShopperWithBarrier[] shoppers=new ShopperWithBarrier[10];
    for (int i=0; i<(shoppers.length/2); i++) {
      shoppers[2*i]=new ShopperWithBarrier("Barron-"+i);
      shoppers[(2*i)+1]=new ShopperWithBarrier("Olivia-"+i);
    }
    for (final ShopperWithBarrier s : shoppers) {
      s.start();
    }
    for (final ShopperWithBarrier s : shoppers) {
      s.join();
    }
    System.out.println("We need to buy "+ShopperWithBarrier.bagsOfChips+" bags of chips.");
  }
}
