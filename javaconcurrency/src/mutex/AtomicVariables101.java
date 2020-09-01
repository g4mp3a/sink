package mutex;


import java.util.concurrent.atomic.*;

class ShopperAtomic extends Thread {

    static AtomicInteger garlicCount = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i=0; i<10_000_000; i++)
            garlicCount.incrementAndGet();
    }
}

public class AtomicVariables101 {
  public static void main(String[] args) throws InterruptedException {
    Thread barron = new ShopperAtomic();
    Thread olivia = new ShopperAtomic();
    barron.start();
    olivia.start();
    barron.join();
    olivia.join();
    System.out.println("We should buy " + ShopperAtomic.garlicCount + " garlic.");
}
}
