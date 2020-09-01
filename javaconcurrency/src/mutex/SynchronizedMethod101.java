package mutex;

class ShopperSynchMethod extends Thread {

  static int garlicCount=0;

  private static synchronized void addGarlic() {
    garlicCount++;
  }

  @Override
  public void run() {
    for (int i=0; i<10_000_000; i++)
      addGarlic();
  }
}

public class SynchronizedMethod101 {
  public static void main(String[] args) throws InterruptedException {
    Thread barron=new ShopperSynchMethod();
    Thread olivia=new ShopperSynchMethod();
    barron.start();
    olivia.start();
    barron.join();
    olivia.join();
    System.out.println("We should buy "+ShopperRentrant102.garlicCount+" garlic.");
  }
}
