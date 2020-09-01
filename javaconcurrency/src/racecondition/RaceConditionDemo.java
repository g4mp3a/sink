package racecondition;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Shopper extends Thread {

  public static int bagsOfChips=1; // start with one on the list
  private static Lock pencil=new ReentrantLock();

  public Shopper(final String name) {
    this.setName(name);
  }

  /**
   * Even though we are using a mutex in the form of the pencil lock,
   * the order in which the threads get scheduled produces different results over runs.
   * Thus we have a race condition.
   * To prevent, that we can use Java synchronization mechanisms like a Barrier or
   * CountDownLatch.
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
    } else { // "Barron"
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

public class RaceConditionDemo {
  public static void main(final String args[]) throws InterruptedException {
    // create 10 shoppers: Barron-0...4 and Olivia-0...4
    final Shopper[] shoppers=new Shopper[10];
    for (int i=0; i<(shoppers.length/2); i++) {
      shoppers[2*i]=new Shopper("Barron-"+i);
      shoppers[(2*i)+1]=new Shopper("Olivia-"+i);
    }
    for (final Shopper s : shoppers) {
      s.start();
    }
    for (final Shopper s : shoppers) {
      s.join();
    }
    System.out.println("We need to buy "+Shopper.bagsOfChips+" bags of chips.");
  }
}
