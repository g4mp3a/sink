package racecondition;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShopperWithCountDownLatch extends Thread {

  public static int bagsOfChips=1; // start with one on the list
  private static Lock pencil=new ReentrantLock();
  private static CountDownLatch fistBump=new CountDownLatch(5);

  public ShopperWithCountDownLatch(final String name) {
    this.setName(name);
  }

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
      // Olivia threads decrement the value of the latch after adding 3 bags
      // Once 5 Olivia threads have counted down the latch, the latch releases
      // signalling the waiting Barron threads to wake up and double the value
      fistBump.countDown();
    } else { // "Barron"
      // Barron threads first wait on the countdown latch till its value reaches 0.
      // At this point the latch releases and the Barron threads double the value.
      try {
        fistBump.await();
      } catch (final InterruptedException e) {
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

public class CountDownLatch101 {
  public static void main(final String args[]) throws InterruptedException {
    // create 10 shoppers: Barron-0...4 and Olivia-0...4
    final ShopperWithCountDownLatch[] shoppers=new ShopperWithCountDownLatch[10];
    for (int i=0; i<(shoppers.length/2); i++) {
      shoppers[2*i]=new ShopperWithCountDownLatch("Barron-"+i);
      shoppers[(2*i)+1]=new ShopperWithCountDownLatch("Olivia-"+i);
    }
    for (final ShopperWithCountDownLatch s : shoppers) {
      s.start();
    }
    for (final ShopperWithCountDownLatch s : shoppers) {
      s.join();
    }
    System.out.println("We need to buy "+ShopperWithCountDownLatch.bagsOfChips+" bags of chips.");
  }
}
