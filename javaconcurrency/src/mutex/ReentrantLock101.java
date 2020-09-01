package mutex;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShopperRentrantLock101 extends Thread {

  static int garlicCount=0;
  static Lock pencil=new ReentrantLock();

  @Override
  public void run() {
    for (int i=0; i<1000000; i++) {
      pencil.lock();
      garlicCount++;
      pencil.unlock();
      System.out.println(Thread.currentThread().getName()+" is thinking.");
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

public class ReentrantLock101 {
  public static void main(String[] args) throws InterruptedException {
    Thread barron=new ShopperRentrantLock101();
    Thread olivia=new ShopperRentrantLock101();
    barron.start();
    olivia.start();
    barron.join();
    olivia.join();
    System.out.println("We should buy "+ShopperAtomic.garlicCount+" garlic.");
  }
}
