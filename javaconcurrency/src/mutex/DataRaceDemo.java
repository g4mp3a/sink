package mutex;

/**
 * Two shoppers adding items to a shared notepad
 */

class Shopper extends Thread {

  static int garlicCount=0;

  @Override
  public void run() {
    for (int i=0; i<10_000_000; i++)
      garlicCount++;
  }
}

public class DataRaceDemo {
  public static void main(String[] args) throws InterruptedException {
    Thread barron=new ShopperRentrant102();
    Thread olivia=new ShopperRentrant102();
    barron.start();
    olivia.start();
    barron.join();
    olivia.join();
    System.out.println("We should buy "+ShopperRentrant102.garlicCount+" garlic.");
  }
}
