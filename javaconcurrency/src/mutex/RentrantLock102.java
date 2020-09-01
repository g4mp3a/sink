package mutex;

import java.util.concurrent.locks.ReentrantLock;

class ShopperRentrant102 extends Thread {

  static int garlicCount, potatoCount=0;
  static ReentrantLock pencil=new ReentrantLock();

  private void addGarlic() {
    pencil.lock();
    System.out.println("Hold count: "+pencil.getHoldCount());
    garlicCount++;
    pencil.unlock();
  }

  private void addPotato() {
    pencil.lock();
    potatoCount++;
    addGarlic();
    pencil.unlock();
  }

  @Override
  public void run() {
    for (int i=0; i<1000000; i++) {
      addGarlic();
      addPotato();
    }
  }
}

public class RentrantLock102 {
  public static void main(String[] args) throws InterruptedException {
    Thread barron=new ShopperRentrant102();
    Thread olivia=new ShopperRentrant102();
    barron.start();
    olivia.start();
    barron.join();
    olivia.join();
    System.out.println("We should buy "+ShopperRentrant102.garlicCount+" garlic.");
    System.out.println("We should buy "+ShopperRentrant102.potatoCount+" potatoes.");
  }
}
