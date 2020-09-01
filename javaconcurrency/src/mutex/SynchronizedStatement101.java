package mutex;

/**
 * Two shoppers adding items to a shared notepad
 */

class ShopperSyncStatement extends Thread {

  static Integer garlicCount=0;

  @Override
  public void run() {
    for (int i=0; i<10_000_000; i++)
      synchronized (ShopperRentrant102.class) {
        garlicCount++;
      }
  }
}

public class SynchronizedStatement101 {
  public static void main(String[] args) throws InterruptedException {
    Thread barron=new ShopperSyncStatement();
    Thread olivia=new ShopperSyncStatement();
    barron.start();
    olivia.start();
    barron.join();
    olivia.join();
    System.out.println("We should buy "+ShopperRentrant102.garlicCount+" garlic.");
  }
}
